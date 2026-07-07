package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Models.Settlement;
import Gnava.Core.Repositories.ISettlementProvider;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class PopulationGrowthEvent extends AbstractGameEventDefinition {
    private final ISettlementProvider settlementProvider;

    public PopulationGrowthEvent(ISettlementProvider settlementProvider) {
        this.settlementProvider = settlementProvider;
    }

    @Override
    protected EventCondition[] conditions() {
        return new EventCondition[] {
            ctx -> settlementProvider.count() >= 1
        };
    }

    @Override
    protected void prepare(EventContext ctx) {
        Settlement settlement = ctx.getSettlementManager().getRandomSettlement();
        ctx.set("target_settlement", settlement);

        int growth = settlement.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
        ctx.set("growth", growth);
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        return "Population grows in %s".formatted(
            ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    protected String resolveDescription(EventContext ctx) {
        return "Population grows in %s by %d".formatted(
            ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new),
            ctx.get("growth", Integer.class).orElseThrow(RuntimeException::new)
        );
    }

    @Override
    protected void apply(EventContext ctx) {
        Settlement settlement = ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        Integer growth = ctx.get("growth", Integer.class).orElseThrow(RuntimeException::new);

        settlement.setMaxPopulation(settlement.getMaxPopulation() + growth);
        settlement.setTotalPopulation(settlement.getTotalPopulation() + growth);
    }
}
