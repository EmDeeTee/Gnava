package Gnava.Core.Events.Contexts;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class WorldEventContext extends EventContext {
    private final WorldStatisticsProvider worldStatisticsProvider;

    public WorldEventContext(
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super(gameState);
        this.worldStatisticsProvider = worldStatisticsProvider;
    }

    public WorldStatistics getStatistics() {
        return worldStatisticsProvider.getWorldStatistics();
    }
}
