package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class NosferatuEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "Nosferatu attacked %s and ate %d %s".formatted(
            context.getRandomTargetSettlement(),
            context.get("damage", Integer.class).orElseThrow(),
            context.getRandomTargetSettlement().getPopulationType().plural()
        );
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Nosferatu!";
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
