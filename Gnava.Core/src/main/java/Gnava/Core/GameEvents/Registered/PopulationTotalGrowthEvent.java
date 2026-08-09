package Gnava.Core.GameEvents.Registered;

import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class PopulationTotalGrowthEvent implements IGameEvent<ISettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "population_total_growth");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.population_total_growth"
    ).weight(0.25)
        .minor()
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        SettlementView settlement = context.settlement();
        int growth = random.nextInt(201);
        context.expandPopulationCapacity(growth);

        return GameEventResult.translated(Map.of(
            "name", settlement.name(),
            "amount", String.valueOf(growth)
        ));
    }
}
