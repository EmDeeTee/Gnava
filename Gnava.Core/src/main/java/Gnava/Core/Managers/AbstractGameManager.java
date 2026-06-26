package Gnava.Core.Managers;

import Gnava.Core.GameState;

public abstract class AbstractGameManager {
    protected final GameState gameState;

    public AbstractGameManager(GameState gameState) {
        this.gameState = gameState;
    }
}
