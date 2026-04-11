package Gnava.Game.Events;

import Gnava.Game.Events.Conditions.EventCondition;
import Gnava.Game.Models.Settlement;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class PopulationGrowthEvent extends AbstractGameEventDefinition {
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
        ctx.set(Settlement.class, settlement);

        int growth = settlement.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
        ctx.set(Integer.class, growth);
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        Settlement settlement = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
        return "Population grows in " + settlement.getName();
    }

    @Override
    protected String resolveDescription(EventContext context) {
        return "Population growth event";
    }

    @Override
    protected void apply(EventContext ctx) {
        Settlement settlement = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
        Integer growth = ctx.get(Integer.class).orElseThrow(RuntimeException::new);

        settlement.setMaxPopulation(settlement.getMaxPopulation() + growth);
        settlement.setTotalPopulation(settlement.getTotalPopulation() + growth);
    }
}
