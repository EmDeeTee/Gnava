package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.AbstractGameEvent;
import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Conditions.Settlement.SettlementNotInSqualorCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public final class SqualorEvent extends AbstractGameEvent<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new SettlementNotInSqualorCondition()
        );
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        context.set("reason", getReason(context));
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().setWealthLevel(SettlementWealthLevel.DESTITUTE);
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.squalor.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.squalor.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        return Map.ofEntries(
            Map.entry("name", context.getRandomTargetSettlement().getName()),
            Map.entry("reason", context.get("reason", String.class).orElseThrow())
        );
    }

    private String getReason(SettlementEventContext context) {
        // TODO: This should also not be here and be translated by the UI
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
