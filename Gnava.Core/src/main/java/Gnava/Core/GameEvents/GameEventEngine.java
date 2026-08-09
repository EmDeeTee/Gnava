package Gnava.Core.GameEvents;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.GameEvents.Contexts.WorldEventContext;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Core.TimeState;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.IGameEventContext;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

@Service
public final class GameEventEngine {
    private final TimeState timeState;
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final ISettlementProvider settlementProvider;
    private final EventRegistry registry;
    private final EventHistory history;
    private final RandomGenerator random;
    private final ApplicationEventPublisher eventPublisher;
    private int lastProcessedDay = Integer.MIN_VALUE;

    public GameEventEngine(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider,
        ISettlementProvider settlementProvider,
        EventRegistry registry,
        EventHistory history,
        RandomGenerator random,
        ApplicationEventPublisher eventPublisher
    ) {
        this.timeState = timeState;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.settlementProvider = settlementProvider;
        this.registry = registry;
        this.history = history;
        this.random = random;
        this.eventPublisher = eventPublisher;
    }

    public synchronized Optional<ExecutedGameEvent> runDay() {
        int currentDay = timeState.getCurrentDay();
        if (lastProcessedDay == currentDay) {
            return Optional.empty();
        }
        lastProcessedDay = currentDay;

        List<EventCandidate<?>> candidates = collectCandidates();
        Optional<EventCandidate<?>> selected = select(candidates);

        if (selected.isEmpty()) {
            return Optional.empty();
        }

        ExecutedGameEvent executed = selected.get().trigger(random, currentDay);
        history.record(executed.id());
        eventPublisher.publishEvent(new ExecutedGameEventReceivedEvent(executed));
        return Optional.of(executed);
    }

    public boolean hasEventHappened(GameEventId eventId) {
        return history.contains(eventId);
    }

    private List<EventCandidate<?>> collectCandidates() {
        List<EventCandidate<?>> candidates = new ArrayList<>();
        WorldEventContext worldContext = new WorldEventContext(timeState, worldStatisticsProvider);
        addEligible(candidates, registry.worldEvents(), worldContext);

        for (Settlement settlement : settlementProvider.getAll()) {
            ISettlementEventContext context = new SettlementEventContext(
                timeState,
                worldStatisticsProvider,
                settlement
            );
            addEligible(candidates, registry.settlementEvents(), context);
        }

        return candidates;
    }

    private <C extends IGameEventContext> void addEligible(
        List<EventCandidate<?>> candidates,
        List<IGameEvent<C>> events,
        C context
    ) {
        for (IGameEvent<C> event : events) {
            EventSpecification spec = event.specification();
            if (!spec.repeatable() && history.contains(spec.id())) {
                continue;
            }
            if (!spec.prerequisites().stream().allMatch(history::contains)) {
                continue;
            }
            if (event.canTrigger(context)) {
                candidates.add(new EventCandidate<>(event, context));
            }
        }
    }

    private Optional<EventCandidate<?>> select(List<EventCandidate<?>> candidates) {
        if (candidates.isEmpty()) {
            return Optional.empty();
        }

        double totalWeight = candidates.stream()
            .mapToDouble(EventCandidate::weight)
            .sum();
        double selectedWeight = random.nextDouble(totalWeight);

        for (EventCandidate<?> candidate : candidates) {
            selectedWeight -= candidate.weight();
            if (selectedWeight < 0.0) {
                return Optional.of(candidate);
            }
        }

        return Optional.of(candidates.getLast());
    }

    private record EventCandidate<C extends IGameEventContext>(IGameEvent<C> event, C context) {
        private double weight() {
            return event.specification().weight();
        }

        private ExecutedGameEvent trigger(RandomGenerator random, int day) {
            EventSpecification spec = event.specification();
            GameEventResult result = event.trigger(context, random);
            String fallbackTitle = result.fallbackTitle().isBlank()
                ? spec.id().toString()
                : result.fallbackTitle();

            return new ExecutedGameEvent(
                spec.id(),
                fallbackTitle,
                result.fallbackDescription(),
                spec.storyEvent(),
                day,
                spec.minor(),
                new TranslationData(spec.translationKey(), result.translationArguments())
            );
        }
    }
}
