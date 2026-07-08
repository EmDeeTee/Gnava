package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.MinimumWorldSettlementsCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public final class PopulationGrowthEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    public PopulationGrowthEvent() { }

    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumWorldSettlementsCondition<>(1)
        );
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        Settlement settlement = context.getSettlementManager().getRandomSettlement();
        context.set("target_settlement", settlement);

        int growth = settlement.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
        context.set("growth", growth);
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Population grows in %s".formatted(
            context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "Population grows in %s by %d".formatted(
            context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new),
            context.get("growth", Integer.class).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        Settlement settlement = context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        Integer growth = context.get("growth", Integer.class).orElseThrow(RuntimeException::new);

        settlement.setMaxPopulation(settlement.getMaxPopulation() + growth);
        settlement.setTotalPopulation(settlement.getTotalPopulation() + growth);
    }

    @Override
    public boolean isMinor() {
        return true;
    }
}
