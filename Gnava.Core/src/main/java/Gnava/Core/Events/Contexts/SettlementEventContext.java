package Gnava.Core.Events.Contexts;

import Gnava.Core.TimeState;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class SettlementEventContext extends WorldEventContext {
    private final SettlementManager settlementManager;
    private final Settlement targetSettlement;

    public SettlementEventContext(
        TimeState timeState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        Settlement targetSettlement
    ) {
        super(timeState, worldStatisticsProvider);
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
