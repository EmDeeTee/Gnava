package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Models.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;

import java.util.Optional;

public class SettlementEventContext extends WorldEventContext {
    private final SettlementManager settlementManager;

    public SettlementEventContext(
        GameState gameState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super(gameState, worldStatisticsProvider);
        this.settlementManager = settlementManager;
    }

    public void getRandomSettlementAsTarget() {
        set("target_settlement", settlementManager.getRandomSettlement());
    }

    public Optional<Settlement> getTargetSettlement() {
        return get("target_settlement", Settlement.class);
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }
}
