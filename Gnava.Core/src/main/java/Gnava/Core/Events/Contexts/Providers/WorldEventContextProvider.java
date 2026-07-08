package Gnava.Core.Events.Contexts.Providers;

import Gnava.Core.Events.Contexts.WorldEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorldEventContextProvider implements IEventContextProvider<WorldEventContext> {
    private final GameState gameState;
    private final WorldStatisticsProvider worldStatisticsProvider;

    private final List<IGameEventDefinition<WorldEventContext>> events;

    public WorldEventContextProvider(
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider,
        List<IGameEventDefinition<WorldEventContext>> events
    ) {
        this.gameState = gameState;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.events = events;
    }

    @Override
    public WorldEventContext buildContext() {
        return new WorldEventContext(gameState, worldStatisticsProvider);
    }

    @Override
    public List<IGameEventDefinition<WorldEventContext>> getEvents() {
        return events;
    }
}