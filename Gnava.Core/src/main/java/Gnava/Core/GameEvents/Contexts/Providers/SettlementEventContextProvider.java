package Gnava.Core.GameEvents.Contexts.Providers;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.TimeState;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;


@Component
public class SettlementEventContextProvider implements IEventContextProvider<SettlementEventContext> {
    private final TimeState timeState;
    private final SettlementManager settlementManager;
    private final WorldStatisticsProvider worldStatisticsProvider;

    private final ISettlementProvider settlementProvider;

    public SettlementEventContextProvider(
        TimeState timeState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        ISettlementProvider settlementProvider
    ) {
        this.timeState = timeState;
        this.settlementManager = settlementManager;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.settlementProvider = settlementProvider;
    }

    @Override
    public SettlementEventContext buildContext() {
        return new SettlementEventContext(
            timeState,
            settlementManager,
            worldStatisticsProvider,
            settlementProvider.getRandom()
        );
    }
}
