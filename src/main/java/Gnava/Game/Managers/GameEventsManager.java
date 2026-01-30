package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Events.EventContext;
import Gnava.Game.Events.GameEvent;
import Gnava.Game.Events.PopulationGrowthEvent;
import Gnava.Game.Events.k_event;
import Gnava.Game.GameState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class GameEventsManager extends GameManager {
    private final EventDispatcher<GameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final List<GameEvent> registeredGameEvents = new ArrayList<>();

    public GameEventsManager(GameState gameState) {
        super(gameState);
        gameState.getTimeManager().addTimeAdvancedListener(this::onTimeAdvanced);
        registerGlobalEvent(PopulationGrowthEvent.populationGrowthEvent(null));
        registerGlobalEvent(k_event.kk_event());
    }

    public void addEventGeneratedListener(Consumer<GameEvent> listener) {
        gameEventDispatcher.addListener(listener);
    }

    public void registerGlobalEvent(GameEvent gameEvent) {
        registeredGameEvents.add(gameEvent);
    }

    public boolean hasEventHappened(GameEvent gameEvent) {
        return false;
        //return registeredGameEvents.contains(gameEvent);
    }

    private void onTimeAdvanced(Integer currentDay) {
        for (Iterator<GameEvent> it = registeredGameEvents.iterator(); it.hasNext();) {
            GameEvent gameEvent = it.next();
            EventContext ctx = new EventContext(null, gameState);

            if (!gameEvent.canRun(ctx)) {
                continue;
            }

            if (gameEvent.isFiresOnce() && gameState.getGameEventsManager().hasEventHappened(gameEvent)) {
                continue;
            }

            gameEvent.happen(ctx);
            gameEventDispatcher.dispatch(gameEvent);
            if (gameEvent.isFiresOnce()) {
                it.remove();
            }
        }
    }
}
