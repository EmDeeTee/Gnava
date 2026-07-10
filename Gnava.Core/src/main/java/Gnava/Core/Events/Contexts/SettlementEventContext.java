package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;

import java.util.Optional;

public class SettlementEventContext extends WorldEventContext {
    private final SettlementManager settlementManager;
    // TODO/NOTE: Maybe drop the Optional?
    private final Optional<Settlement> targetSettlement;

    public SettlementEventContext(
        GameState gameState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        Settlement targetSettlement
    ) {
        super(gameState, worldStatisticsProvider);
        this.settlementManager = settlementManager;
        this.targetSettlement = Optional.ofNullable(targetSettlement);
    }

    public Optional<Settlement> getRandomTargetSettlement() {
        return targetSettlement;
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }
}
