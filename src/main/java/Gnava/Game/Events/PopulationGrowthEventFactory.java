package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;
import Gnava.Game.Models.Settlement;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class PopulationGrowthEventFactory implements GameEventFactory {
    private final Settlement target;

    public PopulationGrowthEventFactory(@Nullable Settlement target) {
        this.target = target;
    }

    @Override
    public GameEvent create() {
        return new GameEventBuilder()
            .prepare(this::prepare)
            .withTitle(ctx -> "Population grows in " + ctx.get(Settlement.class).orElseThrow(RuntimeException::new))
            .withDescription("Population growth event")
            .when(this::when)
            .action(this::action)
            .build();
    }

    private void prepare(EventContext ctx) {
        Settlement s = (target != null) ? target : ctx.getGameState().getSettlementManager().getRandomSettlement();
        ctx.set(Settlement.class, s);

        int growth = s.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
        ctx.set(Integer.class, growth);
    }

    private void action(EventContext ctx) {
        Settlement s = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
        Integer add = ctx.get(Integer.class).orElseThrow(RuntimeException::new);

        s.setMaxPopulation(s.getMaxPopulation() + add);
        s.setTotalPopulation(s.getTotalPopulation() + add);
    }

    private boolean when(EventContext ctx) {
        return ctx.getGameState().getSettlementManager().getSettlementCount() >= 1;
    }
}