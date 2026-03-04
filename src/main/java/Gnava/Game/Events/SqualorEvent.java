package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;
import Gnava.Game.Models.Settlement;

public class SqualorEvent implements GameEventFactory {
    @Override
    public GameEvent create() {
        return new GameEventBuilder()
            .prepare(ctx ->
                ctx.set(
                    Settlement.class,
                    ctx.getGameState().getSettlementManager().getRandomSettlement()
                )
            )
            .withTitle(ctx -> "Squalor hits " + ctx.get(Settlement.class).get().getName())
            .build();
    }
}
