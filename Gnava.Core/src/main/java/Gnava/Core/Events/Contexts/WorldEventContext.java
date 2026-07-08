package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;

public class WorldEventContext extends EventContext {
    public WorldEventContext(
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super(gameState, worldStatisticsProvider);
    }
}
