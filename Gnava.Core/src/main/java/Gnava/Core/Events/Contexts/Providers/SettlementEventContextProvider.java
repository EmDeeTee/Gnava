package Gnava.Core.Events.Contexts.Providers;

import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.Contexts.WorldEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.GameState;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettlementEventContextProvider implements IEventContextProvider<SettlementEventContext> {
    private final GameState gameState;
    private final SettlementManager settlementManager;
    private final WorldStatisticsProvider worldStatisticsProvider;

    private final List<IGameEventDefinition<SettlementEventContext>> events;

    public SettlementEventContextProvider(
        GameState gameState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        List<IGameEventDefinition<SettlementEventContext>> events
    ) {
        this.gameState = gameState;
        this.settlementManager = settlementManager;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.events = events;
    }

    @Override
    public SettlementEventContext buildContext() {
        return new SettlementEventContext(gameState, settlementManager, worldStatisticsProvider);
    }

    @Override
    public List<IGameEventDefinition<SettlementEventContext>> getEvents() {
        return events;
    }
}
