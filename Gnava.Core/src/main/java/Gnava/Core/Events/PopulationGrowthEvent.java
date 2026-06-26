package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Models.Settlement;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class PopulationGrowthEvent extends AbstractGameEvent {
    private final Settlement target;

    private PopulationGrowthEvent(@Nullable Settlement target) {
        this.target = target;
    }

    public static PopulationGrowthEvent create(@Nullable Settlement target) {
        return new PopulationGrowthEvent(target);
    }

    @Override
    protected EventCondition[] conditions() {
        return new EventCondition[] {
            ctx -> ctx.getGameState().getSettlementManager().getSettlementCount() >= 1
        };
    }

    @Override
    protected void prepare(EventContext ctx) {
        Settlement settlement = (target != null)
            ? target
            : ctx.getGameState().getSettlementManager().getRandomSettlement();
        ctx.set("target_settlement", settlement);

        int growth = settlement.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
        ctx.set("growth", growth);
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        Settlement settlement = ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        return "Population grows in " + settlement.getName();
    }

    @Override
    protected String resolveDescription(EventContext context) {
        return "Population growth event";
    }

    @Override
    protected void apply(EventContext ctx) {
        Settlement settlement = ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        Integer growth = ctx.get("growth", Integer.class).orElseThrow(RuntimeException::new);

        settlement.setMaxPopulation(settlement.getMaxPopulation() + growth);
        settlement.setTotalPopulation(settlement.getTotalPopulation() + growth);
    }
}
