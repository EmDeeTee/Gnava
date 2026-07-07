package Gnava.Core.Managers;

import Gnava.Core.EventDispatcher;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Core.Events.IGameEvent;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

@Service
public class GameEventManager extends AbstractGameManager {
    private final EventDispatcher<ExecutedGameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final List<IGameEvent> registeredGameEvents;
    private final Set<IGameEvent> executedGameEvents = new HashSet<>();
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final SettlementManager settlementManager;

    public GameEventManager(
        GameState gameState,
        TimeManager timeManager,
        WorldStatisticsProvider worldStatisticsProvider,
        SettlementManager settlementManager,
        List<IGameEvent> events
    ) {
        super(gameState);
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.settlementManager = settlementManager;
        timeManager.addTimeAdvancedListener(this::onTimeAdvanced);
        this.registeredGameEvents = new ArrayList<>(events);
    }

    public void addEventExecutedListener(Consumer<ExecutedGameEvent> listener) {
        gameEventDispatcher.addListener(listener);
    }

    private @NotNull Optional<EventCandidates> generateEventCandidates() {
        // TODO: Passing the target to the EventContext seems like a bad idea, especially because no we have attachments
        // TODO: I also want to add some logging. Wih a nice Log class with configuration
        EventContext eventContext = new EventContext(null, gameState, worldStatisticsProvider, settlementManager);
        List<IGameEvent> eligibleEvents = new ArrayList<>();
        double totalWeight = 0.0;

        for (IGameEvent event : registeredGameEvents) {
            if (!event.canRun(eventContext)) {
                continue;
            }
            if (executedGameEvents.contains(event) && event.firesOnce()) {
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

        return Optional.of(new EventCandidates(eligibleEvents, totalWeight, eventContext));
    }

    private IGameEvent selectEventFromCandidates(EventCandidates candidates) {
        double randomValue = ThreadLocalRandom.current().nextDouble() * candidates.totalWeight();
        double accumulatedWeight = 0.0;

        for (IGameEvent event : candidates.candidates()) {
            accumulatedWeight += event.probability();
            if (randomValue < accumulatedWeight) {
                return event;
            }
        }

        return candidates.candidates().getLast();
    }

    private void onTimeAdvanced(Integer currentDay) {
        generateEventCandidates().ifPresent(this::executeEvent);
    }

    private void executeEvent(EventCandidates candidates) {
        IGameEvent selectedEvent = selectEventFromCandidates(candidates);

        ExecutedGameEvent generatedEvent = selectedEvent.happen(candidates.context());
        gameEventDispatcher.dispatch(generatedEvent);
        executedGameEvents.add(selectedEvent);
    }
}
