package Gnava.Core.Managers;

import Gnava.Core.EventDispatcher;
import Gnava.Core.Events.EventContext;
import Gnava.Core.Events.GameEvent;
import Gnava.Core.Events.IGameEvent;
import Gnava.Core.Events.KEvent;
import Gnava.Core.Events.PopulationGrowthEvent;
import Gnava.Core.Events.SqualorEvent;
import Gnava.Core.GameState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class GameEventManager extends AbstractGameManager {
    private final EventDispatcher<GameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final List<IGameEvent> registeredGameEvents = new ArrayList<>();

    public GameEventManager(GameState gameState) {
        super(gameState);
        gameState.getTimeManager().addTimeAdvancedListener(this::onTimeAdvanced);
        registerGlobalEvent(PopulationGrowthEvent.create(null));
        registerGlobalEvent(SqualorEvent.create());
        registerGlobalEvent(KEvent.create());
    }

    public void addEventGeneratedListener(Consumer<GameEvent> listener) {
        gameEventDispatcher.addListener(listener);
    }

    public void registerGlobalEvent(IGameEvent gameEvent) {
        registeredGameEvents.add(gameEvent);
    }

    private @NotNull Optional<EventCandidates> generateEventCandidates() {
        // TODO: Passing the target to the EventContext seems like a bad idea, especially because no we have attachments
        // TODO: I also want to add some logging. Wih a nice Log class with configuration
        EventContext eventContext = new EventContext(null, gameState);
        List<IGameEvent> eligibleEvents = new ArrayList<>();
        double totalWeight = 0.0;

        for (IGameEvent event : registeredGameEvents) {
            if (!event.canRun(eventContext)) {
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
        Optional<EventCandidates> maybe = generateEventCandidates();
        if (maybe.isEmpty()) {
            return;
        }

        EventCandidates candidates = maybe.get();
        IGameEvent selectedEvent = selectEventFromCandidates(candidates);
        GameEvent generatedEvent = selectedEvent.happen(candidates.context());
        gameEventDispatcher.dispatch(generatedEvent);
        if (selectedEvent.firesOnce()) {
            registeredGameEvents.remove(selectedEvent);
        }
    }
}
