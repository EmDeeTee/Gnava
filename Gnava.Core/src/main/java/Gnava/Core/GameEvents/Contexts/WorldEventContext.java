package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.TimeState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Core.Statistics.Records.WorldStatistics;
import Gnava.GameApi.GameEvents.IGameEventContext;

public class WorldEventContext implements IGameEventContext {
    private final TimeState timeState;
    private final WorldStatisticsProvider worldStatisticsProvider;

    public WorldEventContext(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        this.timeState = timeState;
        this.worldStatisticsProvider = worldStatisticsProvider;
    }

    @Override
    public int currentDay() {
        return timeState.getCurrentDay();
    }

    public WorldStatistics worldStatistics() {
        return worldStatisticsProvider.getWorldStatistics();
    }
}
