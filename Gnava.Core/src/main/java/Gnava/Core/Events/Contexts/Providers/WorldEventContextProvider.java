package Gnava.Core.Events.Contexts.Providers;

import Gnava.Core.Events.Contexts.WorldEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.TimeState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorldEventContextProvider implements IEventContextProvider<WorldEventContext> {
    private final TimeState timeState;
    private final WorldStatisticsProvider worldStatisticsProvider;

    private final List<IGameEventDefinition<WorldEventContext>> events;

    public WorldEventContextProvider(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider,
        List<IGameEventDefinition<WorldEventContext>> events
    ) {
        this.timeState = timeState;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.events = events;
    }

    @Override
    public WorldEventContext buildContext() {
        return new WorldEventContext(timeState, worldStatisticsProvider);
    }

    @Override
    public List<IGameEventDefinition<WorldEventContext>> getEvents() {
        return events;
    }
}