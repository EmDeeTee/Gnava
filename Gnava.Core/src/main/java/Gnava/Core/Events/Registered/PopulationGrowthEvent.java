package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.SettlementHasFreePopulationCapacityCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumWorldSettlementsCountCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.AddPopulationResult;
import Gnava.Core.Settlements.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class PopulationGrowthEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumWorldSettlementsCountCondition<>(1),
            new SettlementHasFreePopulationCapacityCondition()
        );
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        Settlement settlement = context.getRandomTargetSettlement();

        int growth = settlement.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(1, 175)
            : ThreadLocalRandom.current().nextInt(1, 33);
        context.set("growth", growth);
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.population_growth.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.population_growth.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        AddPopulationResult result = context.get("addResult", AddPopulationResult.class).orElseThrow();

        return Map.ofEntries(
            Map.entry("name", context.getRandomTargetSettlement().getName()),
            Map.entry("amount", String.valueOf(result.addedAmount())),
            Map.entry(
                "rejectedText",
                result.overflow() > 0 ? "(%d population rejected due to lack of space)".formatted(result.overflow()) : "" // TODO: This text should also be a UI concern
            )
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        Settlement settlement = context.getRandomTargetSettlement();
        Integer growth = context.get("growth", Integer.class).orElseThrow(RuntimeException::new);

        AddPopulationResult result = settlement.addPopulation(growth);
        context.set("addResult", result);
    }

    @Override
    public boolean isMinor() {
        return true;
    }
}
