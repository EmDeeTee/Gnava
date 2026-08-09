package Gnava.Core.GameEvents.Registered;

import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.PopulationChange;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class PopulationGrowthEvent implements IGameEvent<ISettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "population_growth");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.population_growth"
    ).minor()
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(ISettlementEventContext context) {
        return context.settlement().populationCapacityRemaining() > 0;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        SettlementView settlement = context.settlement();
        int growth = settlement.maxPopulation() > 1000
            ? random.nextInt(1, 175)
            : random.nextInt(1, 33);
        PopulationChange result = context.addPopulation(growth);

        return GameEventResult.translated(Map.of(
            "name", settlement.name(),
            "amount", String.valueOf(result.addedAmount()),
            "rejectedText",
            result.overflow() > 0
                ? "(%d population rejected due to lack of space)".formatted(result.overflow())
                : ""
        ));
    }
}
