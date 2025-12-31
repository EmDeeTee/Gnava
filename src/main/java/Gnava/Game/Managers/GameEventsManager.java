package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Events.Simulation.DebugEvent;
import Gnava.Game.Events.Simulation.GameEvent;

public class GameEventsManager implements DispatchableManager<GameEvent> {
    private final EventDispatcher<GameEvent> dispatcher = new EventDispatcher<>();

    public void generate() {
        GameEvent gameEvent = new DebugEvent("Game manager event", "Hello");
        dispatcher.dispatch(gameEvent);
    }

    @Override
    public EventDispatcher<GameEvent> getDispatcher() {
        return dispatcher;
    }
}
