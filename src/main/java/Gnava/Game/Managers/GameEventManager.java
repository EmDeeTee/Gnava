package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Events.*;
import Gnava.Game.GameState;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class GameEventManager extends GameManager {
    private final EventDispatcher<GameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final List<GameEvent> registeredGameEvents = new ArrayList<>();

    public GameEventManager(GameState gameState) {
        super(gameState);
        gameState.getTimeManager().addTimeAdvancedListener(this::onTimeAdvanced);
        registerGlobalEvent(new PopulationGrowthEventFactory(null).create());
        registerGlobalEvent(new SqualorEvent().create());
        registerGlobalEvent(k_event.kk_event());
    }

    public void addEventGeneratedListener(Consumer<GameEvent> listener) {
        gameEventDispatcher.addListener(listener);
    }

    // FIXME: We should not reuse the same event instances. Mark executed events as done and move on
    public void registerGlobalEvent(GameEvent gameEvent) {
        registeredGameEvents.add(gameEvent);
    }

    public boolean hasEventHappened(GameEvent gameEvent) {
        return false;
        //return registeredGameEvents.contains(gameEvent);
    }

    private @NotNull Optional<EventCandidates> generateEventCandidates() {
        // TODO: Passing the target to the EventContext seems like a bad idea, especially because no we have attachments
        // TODO: I also want to add some logging. Wih a nice Log class with configuration
        EventContext eventContext = new EventContext(null, gameState);
        List<GameEvent> eligibleEvents = new ArrayList<>();
        double totalWeight = 0.0;

        for (GameEvent event : registeredGameEvents) {
            if (!event.canRun(eventContext)) {
                continue;
            }

            if (event.isFiresOnce() && gameState.getGameEventsManager().hasEventHappened(event)) {
                continue;
            }

            float weight = event.getProbability();
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

    private GameEvent selectEventFromCandidates(EventCandidates candidates) {
        double randomValue = ThreadLocalRandom.current().nextDouble() * candidates.totalWeight();
        double accumulatedWeight = 0.0;

        for (GameEvent event : candidates.candidates()) {
            accumulatedWeight += event.getProbability();
            if (randomValue < accumulatedWeight) {
                return event;
            }
        }

        // TODO: What if there are no candidates?
        return candidates.candidates().getLast();
    }

    private void onTimeAdvanced(Integer currentDay) {
        Optional<EventCandidates> maybe = generateEventCandidates();
        if (maybe.isEmpty()) {
            return;
        }

        EventCandidates candidates = maybe.get();
        GameEvent selectedEvent = selectEventFromCandidates(candidates);

        selectedEvent.happen(candidates.context());
        gameEventDispatcher.dispatch(selectedEvent);
        if (selectedEvent.isFiresOnce()) {
            registeredGameEvents.remove(selectedEvent);
        }
    }
}
