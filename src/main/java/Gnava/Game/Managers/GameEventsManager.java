package Gnava.Game.Managers;

import Gnava.Game.Events.Simulation.GameEvent;
import Gnava.Game.Events.Simulation.PopulationGrowthEvent;
import Gnava.Game.Events.Simulation.TextEvent;
import Gnava.Game.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class GameEventsManager extends DispatchableManager<GameEvent> {
    private final GameEvent[] storyEvents = new GameEvent[] {
        new TextEvent("A mysterious figure seen in the distance", ""),
        new TextEvent("KKSID", ""),
    };

    private final List<GameEvent> pastStoryEvents = new ArrayList<>();

    public void addEventGeneratedListener(Consumer<GameEvent> listener) {
        getDispatcher().addListener(listener);
    }

    public void generate() {
        GameEvent gameEvent = getNextEvent();
        if (gameEvent.canRun()) {
            dispatcher.dispatch(gameEvent);
            gameEvent.happen();
            pastStoryEvents.add(gameEvent);
        }
    }

    public boolean hasEventHappened(GameEvent gameEvent) {
        return pastStoryEvents.contains(gameEvent);
    }

    private GameEvent getNextEvent() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int chance = random.nextInt(0, 100);

        // TODO: This needs higher level abstraction
//        if (chance <= 10 && GameState.getInstance().getWorldPopulation() > 5000) {
//            pastStoryEvents.add(storyEvents[0]);
//            return storyEvents[0];
//        }
//        if (chance <= 10 && pastStoryEvents.contains(storyEvents[0]) && GameState.getInstance().getWorldPopulation() > 5000) {
//            pastStoryEvents.add(storyEvents[1]);
//            return storyEvents[1];
//        }
        return new PopulationGrowthEvent(GameState.getInstance().getSettlementManager().getRandomSettlement());
    }
}
