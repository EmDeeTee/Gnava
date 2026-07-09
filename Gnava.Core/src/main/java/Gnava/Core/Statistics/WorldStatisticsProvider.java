package Gnava.Core.Statistics;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Managers.Settlement.SettlementStatisticsManager;
import Gnava.Core.Repositories.ISettlementProvider;
import org.springframework.stereotype.Service;

@Service
public class WorldStatisticsProvider {
    private final ISettlementProvider settlementProvider;
    private final SettlementStatisticsManager settlementStatisticsManager;

    public WorldStatisticsProvider(
        ISettlementProvider settlementProvider,
        SettlementStatisticsManager settlementStatisticsManager
    ) {
        this.settlementProvider = settlementProvider;
        this.settlementStatisticsManager = settlementStatisticsManager;
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementStatisticsManager.getWorldPopulation(),
            settlementProvider.count()
        );
    }
}
