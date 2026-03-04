package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Events.*;
import Gnava.Game.GameState;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class GameEventsManager extends GameManager {
    private final EventDispatcher<GameEvent> gameEventDispatcher = new EventDispatcher<>();
    private final List<GameEvent> registeredGameEvents = new ArrayList<>();
    private final Map<Class<?>, Integer> df = new HashMap<>();

    public GameEventsManager(GameState gameState) {
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

    private void onTimeAdvanced(Integer currentDay) {
        //select a random event ..
        GameEvent gameEvent = registeredGameEvents.get( ThreadLocalRandom.current( ).nextInt( 0, registeredGameEvents.size( ) - 1) );
        //construct our event context ..
        EventContext ctx = new EventContext(null, gameState);

        if ( ! gameEvent.canRun( ctx ) ){
            return;
        }
        if ( gameEvent.isFiresOnce( ) && gameState.getGameEventsManager().hasEventHappened( gameEvent ) ){
            return;
        }

        gameEvent.happen( ctx );
        gameEventDispatcher.dispatch( gameEvent );
        if (gameEvent.isFiresOnce()){
            registeredGameEvents.remove(gameEvent);
        }

        // NOTE: I fucking hate you kksidd, I really do
    }
}
