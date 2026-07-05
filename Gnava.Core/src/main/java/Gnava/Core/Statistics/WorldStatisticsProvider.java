package Gnava.Core.Statistics;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Repositories.ISettlementProvider;
import org.springframework.stereotype.Service;

@Service
public class WorldStatisticsProvider {
    private final ISettlementProvider settlementProvider;
    private final SettlementManager settlementManager;

    public WorldStatisticsProvider(
        ISettlementProvider settlementProvider,
        SettlementManager settlementManager
    ) {
        this.settlementProvider = settlementProvider;
        this.settlementManager = settlementManager;
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementManager.getWorldPopulation(),
            settlementProvider.count()
        );
    }
}
