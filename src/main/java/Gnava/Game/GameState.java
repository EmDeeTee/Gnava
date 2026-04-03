package Gnava.Game;

import Gnava.Game.DataTransferObjects.WorldStatistics;
import Gnava.Game.Managers.GameEventManager;
import Gnava.Game.Managers.SettlementManager;
import Gnava.Game.Managers.TimeManager;
import Gnava.Game.Managers.VictoryConditionManager;

public class GameState {
    private final SettlementManager settlementManager;
    private final TimeManager timeManager;
    private final GameEventManager gameEventManager;
    private final VictoryConditionManager victoryConditionManager;

    public GameState() {
        settlementManager = new SettlementManager(this);
        timeManager = new TimeManager(this);
        gameEventManager = new GameEventManager(this);
        victoryConditionManager = new VictoryConditionManager(this);
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementManager.getWorldPopulation(),
            settlementManager.getSettlementCount()
        );
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public GameEventManager getGameEventsManager() {
        return gameEventManager;
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }

    public VictoryConditionManager getVictoryConditionsManager() {
        return victoryConditionManager;
    }
}
