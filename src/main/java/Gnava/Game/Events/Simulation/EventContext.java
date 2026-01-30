package Gnava.Game.Events.Simulation;

import Gnava.Game.GameState;

public final class EventContext {
    private final Object subject;
    private final GameState gameState;

    public EventContext(Object subject, GameState gameState) {
        this.subject = subject;
        this.gameState = gameState;
    }

    public Object getSubject() {
        return subject;
    }

    public GameState getGameState() {
        return gameState;
    }
}
