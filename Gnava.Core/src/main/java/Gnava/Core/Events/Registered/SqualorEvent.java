package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.SettlementNotInSqualorCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.TranslationData;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.Core.Settlements.Settlement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class SqualorEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new SettlementNotInSqualorCondition()
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().setWealthLevel(SettlementWealthLevel.DESTITUTE);
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return getReason(context);
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        Settlement settlement = context.getRandomTargetSettlement();
        return "Squalor hits " + settlement.getName();
    }

    @Override
    protected TranslationData getTranslationData() {
        return null;
    }

    private String getReason(SettlementEventContext context) {
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(context.getRandomTargetSettlement()),
            "%s got hit with a Squalormelon".formatted(context.getRandomTargetSettlement())
        };

        return reasons[ThreadLocalRandom.current().nextInt(reasons.length)];
    }

    @Override
    public float probability() {
        return 0.050f;
    }
}
