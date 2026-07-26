package Gnava.Core.Events.Contexts.Providers;

import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.TimeState;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettlementEventContextProvider implements IEventContextProvider<SettlementEventContext> {
    private final TimeState timeState;
    private final SettlementManager settlementManager;
    private final WorldStatisticsProvider worldStatisticsProvider;

    private final ISettlementProvider settlementProvider;
    private final List<IGameEventDefinition<SettlementEventContext>> events;

    public SettlementEventContextProvider(
        TimeState timeState,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider,
        ISettlementProvider settlementProvider,
        List<IGameEventDefinition<SettlementEventContext>> events
    ) {
        this.timeState = timeState;
        this.settlementManager = settlementManager;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.settlementProvider = settlementProvider;
        this.events = events;
    }

    @Override
    public SettlementEventContext buildContext() {
        return new SettlementEventContext(
                timeState,
            settlementManager,
            worldStatisticsProvider,
            settlementProvider.getRandom()
        );
    }

    @Override
    public List<IGameEventDefinition<SettlementEventContext>> getEvents() {
        return events;
    }
}
