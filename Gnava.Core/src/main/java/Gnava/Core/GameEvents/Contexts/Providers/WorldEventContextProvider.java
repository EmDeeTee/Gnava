package Gnava.Core.GameEvents.Contexts.Providers;

import Gnava.Core.GameEvents.Contexts.WorldEventContext;
import Gnava.Core.TimeState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;

@Component
public class WorldEventContextProvider implements IEventContextProvider<WorldEventContext> {
    private final TimeState timeState;
    private final WorldStatisticsProvider worldStatisticsProvider;

    public WorldEventContextProvider(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        this.timeState = timeState;
        this.worldStatisticsProvider = worldStatisticsProvider;
    }

    @Override
    public WorldEventContext buildContext() {
        return new WorldEventContext(timeState, worldStatisticsProvider);
    }
}
