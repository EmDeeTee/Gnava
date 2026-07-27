package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.TimeState;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class WorldEventContext extends EventContext {
    public WorldEventContext(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super(timeState, worldStatisticsProvider);
    }
}
