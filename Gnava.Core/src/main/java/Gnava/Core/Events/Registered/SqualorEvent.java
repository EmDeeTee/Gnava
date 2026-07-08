package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.SettlementNotInSqualorCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Enums.SettlementWealthLevel;
import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public final class SqualorEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new SettlementNotInSqualorCondition()
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().orElseThrow().setWealthLevel(SettlementWealthLevel.DESTITUTE);
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return getReason(context);
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        Settlement settlement = context.getRandomTargetSettlement().orElseThrow();
        return "Squalor hits " + settlement.getName();
    }

    private String getReason(SettlementEventContext context) {
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(context.getRandomTargetSettlement().orElseThrow())
        };

        return reasons[ThreadLocalRandom.current().nextInt(reasons.length)];
    }
}
