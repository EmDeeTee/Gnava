package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.AddPopulationResult;
import Gnava.Core.Settlements.Settlement;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.Map;
import java.util.random.RandomGenerator;

public final class PopulationGrowthEvent implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "population_growth");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.population_growth"
    ).minor().build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        return context.settlement().getPopulationCapacityRemaining() > 0;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        Settlement settlement = context.settlement();
        int growth = settlement.getMaxPopulation() > 1000
            ? random.nextInt(1, 175)
            : random.nextInt(1, 33);
        AddPopulationResult result = settlement.addPopulation(growth);

        return GameEventResult.translated(Map.of(
            "name", settlement.getName(),
            "amount", String.valueOf(result.addedAmount()),
            "rejectedText",
            result.overflow() > 0
                ? "(%d population rejected due to lack of space)".formatted(result.overflow())
                : ""
        ));
    }
}
