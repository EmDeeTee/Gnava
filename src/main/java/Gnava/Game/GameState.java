package Gnava.Game;

import Gnava.Game.DataTransferObjects.WorldStatistics;
import Gnava.Game.Managers.GameEventsManager;
import Gnava.Game.Managers.SettlementsManager;
import Gnava.Game.Managers.TimeManager;
import Gnava.Game.Managers.VictoryConditionsManager;

public class GameState {
    private final SettlementsManager settlementManager;
    private final TimeManager timeManager;
    private final GameEventsManager gameEventsManager;
    private final VictoryConditionsManager victoryConditionsManager;

    public GameState() {
        settlementManager = new SettlementsManager(this);
        timeManager = new TimeManager(this);
        gameEventsManager = new GameEventsManager(this);
        victoryConditionsManager = new VictoryConditionsManager(this);
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

    public GameEventsManager getGameEventsManager() {
        return gameEventsManager;
    }

    public SettlementsManager getSettlementManager() {
        return settlementManager;
    }

    public VictoryConditionsManager getVictoryConditionsManager() {
        return victoryConditionsManager;
    }
}
