package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class SettlementEventContext extends WorldEventContext {
    private final SettlementManager settlementManager;
    private final Settlement targetSettlement;

    public SettlementEventContext(
        GameState gameState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        Settlement targetSettlement
    ) {
        super(gameState, worldStatisticsProvider);
        this.settlementManager = settlementManager;
        this.targetSettlement = targetSettlement;
    }

    public Settlement getRandomTargetSettlement() {
        return targetSettlement;
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }
}
