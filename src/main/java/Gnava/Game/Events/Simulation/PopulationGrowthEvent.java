package Gnava.Game.Events.Simulation;

import Gnava.Game.Settlements.Settlement;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class PopulationGrowthEvent {
    public static GameEvent populationGrowthEvent(@Nullable Settlement target) {
        return new GameEventBuilder()
            .withTitle("Population grows")
            .withDescription("Population growth event")
            .when(ctx -> ctx.getGameState().getSettlementManager().getSettlementCount() >= 1)
            .action(ctx -> {
                Settlement s = target != null ? target : ctx.getGameState().getSettlementManager().getRandomSettlement();
                int add = s.getMaxPopulation() > 1000
                    ? ThreadLocalRandom.current().nextInt(0, 50)
                    : ThreadLocalRandom.current().nextInt(0, 200);

                s.setMaxPopulation(s.getMaxPopulation() + add);
                s.setTotalPopulation(s.getTotalPopulation() + add);
            })
            .build();
    }
}