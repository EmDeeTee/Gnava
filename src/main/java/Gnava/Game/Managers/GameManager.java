package Gnava.Game.Managers;

import Gnava.Game.GameState;

public abstract class GameManager {
    protected final GameState gameState;

    public GameManager(GameState gameState) {
        this.gameState = gameState;
    }
}
