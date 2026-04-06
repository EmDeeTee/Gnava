package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;
import Gnava.Game.Events.Conditions.EventCondition;
import Gnava.Game.Models.Settlement;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class PopulationGrowthEvent {
    private PopulationGrowthEvent() { }

    public static GameEvent create(@Nullable Settlement target) {
        return new GameEventBuilder()
            .prepare(new PrepareAction(target))
            .withTitle(new TitleProvider())
            .withDescription("Population growth event")
            .when(new PopulationCondition())
            .action(new ApplyGrowthAction())
            .build();
    }

    private record PrepareAction(Settlement target) implements EventAction {
        private PrepareAction(@Nullable Settlement target) {
            this.target = target;
        }

        @Override
        public void execute(EventContext ctx) {
            Settlement s = (target != null) ? target : ctx.getGameState().getSettlementManager().getRandomSettlement();
            ctx.set(Settlement.class, s);

            int growth = s.getMaxPopulation() > 1000
                ? ThreadLocalRandom.current().nextInt(0, 50)
                : ThreadLocalRandom.current().nextInt(0, 200);
            ctx.set(Integer.class, growth);
        }
    }

    private static final class TitleProvider implements EventTitleProvider {
        @Override
        public String getTitle(EventContext ctx) {
            Settlement s = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
            return "Population grows in " + s.getName();
        }
    }

    private static final class PopulationCondition implements EventCondition {
        @Override
        public boolean isSatisfied(EventContext eventContext) {
            return eventContext.getGameState().getSettlementManager().getSettlementCount() >= 1;
        }
    }

    private static final class ApplyGrowthAction implements EventAction {
        @Override
        public void execute(EventContext ctx) {
            Settlement s = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
            Integer add = ctx.get(Integer.class).orElseThrow(RuntimeException::new);

            s.setMaxPopulation(s.getMaxPopulation() + add);
            s.setTotalPopulation(s.getTotalPopulation() + add);
        }
    }
}
