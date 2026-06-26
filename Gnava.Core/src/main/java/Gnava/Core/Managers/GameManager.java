package Gnava.Core.Managers;

import Gnava.Core.GameState;

public abstract class GameManager {
    protected final GameState gameState;

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }
}
