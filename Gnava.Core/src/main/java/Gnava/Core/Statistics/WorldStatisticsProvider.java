package Gnava.Core.Statistics;

import Gnava.Core.Statistics.Records.WorldStatistics;
import Gnava.Core.Statistics.Services.PlayerBodyCountTallyService;
import Gnava.Core.Statistics.Services.SettlementStatisticsManager;
import Gnava.Core.Repositories.ISettlementProvider;
import org.springframework.stereotype.Service;

@Service
public final class WorldStatisticsProvider {
    private final ISettlementProvider settlementProvider;
    private final SettlementStatisticsManager settlementStatisticsManager;
    private final PlayerBodyCountTallyService playerBodyCountTallyService;

    public WorldStatisticsProvider(
        ISettlementProvider settlementProvider,
        SettlementStatisticsManager settlementStatisticsManager,
        PlayerBodyCountTallyService playerBodyCountTallyService
    ) {
        this.settlementProvider = settlementProvider;
        this.settlementStatisticsManager = settlementStatisticsManager;
        this.playerBodyCountTallyService = playerBodyCountTallyService;
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementStatisticsManager.getWorldPopulation(),
            settlementProvider.count(),
            playerBodyCountTallyService.getPlayerBodyCount()
        );
    }
}
