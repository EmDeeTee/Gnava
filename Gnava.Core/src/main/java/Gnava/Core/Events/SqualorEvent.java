package Gnava.Core.Events;

import Gnava.Core.Models.Settlement;

public final class SqualorEvent extends AbstractGameEventDefinition {
    private SqualorEvent() { }

    public static SqualorEvent create() {
        return new SqualorEvent();
    }

    @Override
    protected void prepare(EventContext ctx) {
        ctx.set(
            Settlement.class,
            ctx.getGameState().getSettlementManager().getRandomSettlement()
        );
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        Settlement settlement = ctx.get(Settlement.class).orElseThrow(RuntimeException::new);
        return "Squalor hits " + settlement.getName();
    }
}
