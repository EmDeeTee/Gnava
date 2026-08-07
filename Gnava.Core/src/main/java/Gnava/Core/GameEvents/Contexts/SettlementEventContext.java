package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.TimeState;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class SettlementEventContext extends WorldEventContext {
    private final Settlement targetSettlement;

    public SettlementEventContext(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider,
        Settlement targetSettlement
    ) {
        super(timeState, worldStatisticsProvider);
        this.targetSettlement = targetSettlement;
    }

    public Settlement settlement() {
        return targetSettlement;
    }
}
