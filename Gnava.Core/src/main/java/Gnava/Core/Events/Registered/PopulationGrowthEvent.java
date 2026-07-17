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
    protected String resolveTitle(SettlementEventContext context) {
        return "Population grows in %s".formatted(
            context.getRandomTargetSettlement()
        );
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
        return Map.ofEntries(
            Map.entry("name", context.getRandomTargetSettlement().getName())
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        AddPopulationResult result = context.get("addResult", AddPopulationResult.class).orElseThrow();
        int added = result.addedAmount();
        int overflow = result.overflow();

        StringBuilder message = new StringBuilder();

        message.append("Population grows in %s by %d".formatted(
            context.getRandomTargetSettlement(),
            added
        ));

        if (overflow > 0) {
            message.append("<br />(")
                .append(overflow)
                .append(" population rejected due to lack of space)");
        }

        return message.toString();
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
