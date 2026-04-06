package Gnava.Game.Events;

import Gnava.Game.Events.Builders.GameEventBuilder;
import Gnava.Game.Models.Settlement;

public final class SqualorEvent {
    private SqualorEvent() { }

    public static GameEvent create() {
        return new GameEventBuilder()
            .prepare(SqualorEvent::prepare)
            .withTitle(SqualorEvent::title)
            .build();
    }

    private static void prepare(EventContext ctx) {
        ctx.set(
            Settlement.class,
            ctx.getGameState().getSettlementManager().getRandomSettlement()
        );
    }

    private static String title(EventContext ctx) {
        Settlement s = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
        return "Squalor hits " + s.getName();
    }
}
