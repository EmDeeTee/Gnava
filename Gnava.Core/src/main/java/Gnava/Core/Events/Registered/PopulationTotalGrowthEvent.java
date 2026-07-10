package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumWorldSettlementsCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Settlement.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class PopulationTotalGrowthEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumWorldSettlementsCondition<>(1)
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "%s has expanded its borders to allow for %d more homes".formatted(
            context.getRandomTargetSettlement().orElseThrow().getName(),
            context.get("growth", Integer.class).orElseThrow()
        );
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "%s expands".formatted(context.getRandomTargetSettlement().orElseThrow().getName());
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        context.set("growth", ThreadLocalRandom.current().nextInt(0, 100 + 1));
    }

    @Override
    protected void apply(SettlementEventContext context) {
        Settlement target = context.getRandomTargetSettlement().orElseThrow();

        target.setMaxPopulation(target.getMaxPopulation() + context.get("growth", Integer.class).orElseThrow());
    }

    @Override
    public float probability() {
        return 0.25f;
    }

    @Override
    public boolean isMinor() {
        return true;
    }
}
