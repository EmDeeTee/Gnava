package Gnava.Core.Managers;

import Gnava.Core.EventDispatcher;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.Contexts.Providers.IEventContextProvider;
import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.GameState;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Service
public final class GameEventManager extends AbstractGameManager {
    private final EventDispatcher<ExecutedGameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final Set<IGameEventDefinition<? extends EventContext>> executedGameEvents = new HashSet<>();

    private final List<IEventContextProvider<? extends EventContext>> eventContextProviders;

    public GameEventManager(
        GameState gameState,
        TimeManager timeManager,
        List<IEventContextProvider<? extends EventContext>> eventContextProviders
    ) {
        super(gameState);
        this.eventContextProviders = eventContextProviders;
        timeManager.addTimeAdvancedListener(this::onTimeAdvanced);
    }

    public void addEventExecutedListener(Consumer<ExecutedGameEvent> listener) {
        gameEventDispatcher.addListener(listener);
    }

    private void onTimeAdvanced(Integer currentDay) {
        for (IEventContextProvider<? extends EventContext> provider : eventContextProviders) {
            processProvider(provider);
        }
    }

    private <T extends EventContext> void processProvider(IEventContextProvider<T> provider) {
        T context = provider.buildContext();
        List<IGameEventDefinition<T>> events = provider.getEvents();

        generateEventCandidates(events, context).ifPresent(candidates -> {
            IGameEventDefinition<T> selectedEvent = selectEventFromCandidates(candidates);

            ExecutedGameEvent generatedEvent = selectedEvent.happen(candidates.context());
            gameEventDispatcher.dispatch(generatedEvent);
            executedGameEvents.add(selectedEvent);
        });
    }

    private <T extends EventContext> Optional<EventCandidates<T>> generateEventCandidates(
        List<IGameEventDefinition<T>> events,
        T context
    ) {
        List<IGameEventDefinition<T>> eligibleEvents = new ArrayList<>();
        double totalWeight = 0.0;

        for (IGameEventDefinition<T> event : events) {
            if (executedGameEvents.contains(event) && event.firesOnce()) {
                continue;
            }
            if (!event.canRun(context)) {
                continue;
            }

            float weight = event.probability();
            if (weight <= 0.0f) {
                continue;
            }

            eligibleEvents.add(event);
            totalWeight += weight;
        }

        if (eligibleEvents.isEmpty() || totalWeight <= 0.0) {
            return Optional.empty();
        }

        return Optional.of(new EventCandidates<>(eligibleEvents, totalWeight, context));
    }

    private <T extends EventContext> IGameEventDefinition<T> selectEventFromCandidates(EventCandidates<T> candidates) {
        double randomValue = ThreadLocalRandom.current().nextDouble() * candidates.totalWeight();
        double accumulatedWeight = 0.0;

        for (IGameEventDefinition<T> event : candidates.candidates()) {
            accumulatedWeight += event.probability();
            if (randomValue < accumulatedWeight) {
                return event;
            }
        }

        return candidates.candidates().getLast();
    }
}
