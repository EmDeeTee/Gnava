package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.AbstractGameEvent;
import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.GameEvents.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class NosferatuEvent extends AbstractGameEvent<SettlementEventContext> {
    @Override
    protected String getTitleTranslationKey() {
        return "events.nosferatu.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.nosferatu.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        Settlement targetSettlement = context.getRandomTargetSettlement();

        return Map.ofEntries(
            Map.entry("name", targetSettlement.getName()),
            Map.entry("amount", String.valueOf(context.get("damage", Integer.class).orElseThrow())),
            Map.entry("plural", targetSettlement.getPopulationType().plural())
        );
    }

    @Override
    public float probability() {
        return 0.1f;
    }

    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumSettlementPopulationCondition(100),
            new MinimumGameDayCondition<>(30)
        );
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        Settlement targetSettlement = context.getRandomTargetSettlement();
        context.set("damage", ThreadLocalRandom.current().nextInt(1, Math.min(200, targetSettlement.getTotalPopulation())) + 1);
    }

    @Override
    protected void apply(SettlementEventContext context) {
        int damage = context.get("damage", Integer.class).orElseThrow();
        Settlement target = context.getRandomTargetSettlement();

        target.addPopulation(-damage);
    }
}
