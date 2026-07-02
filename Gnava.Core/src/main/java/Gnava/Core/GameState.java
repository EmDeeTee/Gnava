package Gnava.Core;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.Managers.GameEventManager;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Managers.TimeManager;
import Gnava.Core.Managers.VictoryConditionManager;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Repositories.ISettlementRepository;

public class GameState {
    private final SettlementManager settlementManager;
    private final TimeManager timeManager;
    private final GameEventManager gameEventManager;
    private final VictoryConditionManager victoryConditionManager;
    private final ISettlementProvider settlementProvider;

    public GameState(ISettlementRepository settlementRepository, ISettlementProvider settlementProvider) {
        settlementManager = new SettlementManager(this, settlementRepository);
        this.settlementProvider = settlementProvider;
        timeManager = new TimeManager(this);
        gameEventManager = new GameEventManager(this, settlementProvider);
        victoryConditionManager = new VictoryConditionManager(this);
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementManager.getWorldPopulation(),
            settlementProvider.count()
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
