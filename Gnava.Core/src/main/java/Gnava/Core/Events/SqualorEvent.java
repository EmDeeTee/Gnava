package Gnava.Core.Events;

import Gnava.Core.Models.Settlement;

public final class SqualorEvent extends AbstractGameEvent {
    private SqualorEvent() { }

    public static SqualorEvent create() {
        return new SqualorEvent();
    }

    @Override
    protected void prepare(EventContext ctx) {
        ctx.set(
            "target_settlement",
            ctx.getSettlementManager().getRandomSettlement()
        );
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        Settlement settlement = ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        return "Squalor hits " + settlement.getName();
    }
}
