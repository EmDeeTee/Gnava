package Gnava.Core.Events;

import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class SqualorEvent extends AbstractGameEvent {
    @Override
    protected void prepare(EventContext ctx) {
        ctx.set(
            "target_settlement",
            ctx.getSettlementManager().getRandomSettlement()
        );
    }

    @Override
    protected String resolveDescription(EventContext context) {
        return getReason(context);
    }

    @Override
    protected String resolveTitle(EventContext ctx) {
        Settlement settlement = ctx.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        return "Squalor hits " + settlement.getName();
    }

    private String getReason(EventContext context) {
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new))
        };

        return reasons[ThreadLocalRandom.current().nextInt(reasons.length)];
    }
}
