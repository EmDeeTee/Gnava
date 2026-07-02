package Gnava.Core;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.Managers.GameEventManager;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Core.Managers.VictoryConditionManager;
import Gnava.Core.Repositories.ISettlementRepository;

public class GameState {
    private final SettlementManager settlementManager;
    private final TimeManager timeManager;
    private final GameEventManager gameEventManager;
    private final VictoryConditionManager victoryConditionManager;

    public GameState(ISettlementRepository settlementRepository) {
        settlementManager = new SettlementManager(this, settlementRepository);
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
