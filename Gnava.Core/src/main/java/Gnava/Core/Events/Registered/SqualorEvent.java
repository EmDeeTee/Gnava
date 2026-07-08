package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

// TODO: Make this event not target already squalored settlements
@Service
public final class SqualorEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected void prepare(SettlementEventContext context) {
        context.set(
            "target_settlement",
            context.getSettlementManager().getRandomSettlement()
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return getReason(context);
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        Settlement settlement = context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new);
        return "Squalor hits " + settlement.getName();
    }

    private String getReason(SettlementEventContext context) {
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(context.get("target_settlement", Settlement.class).orElseThrow(RuntimeException::new))
        };

        return reasons[ThreadLocalRandom.current().nextInt(reasons.length)];
    }
}
