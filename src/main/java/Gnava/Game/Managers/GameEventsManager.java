package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Events.Simulation.GameEvent;
import Gnava.Game.Events.Simulation.PopulationGrowthEvent;
import Gnava.Game.GameState;

public class GameEventsManager implements DispatchableManager<GameEvent> {
    private final EventDispatcher<GameEvent> dispatcher = new EventDispatcher<>();

    public void generate() {
        GameEvent gameEvent = new PopulationGrowthEvent(GameState.getInstance().getRandomSettlement());
        dispatcher.dispatch(gameEvent);
        gameEvent.happen();
    }

    @Override
    public EventDispatcher<GameEvent> getDispatcher() {
        return dispatcher;
    }
}
