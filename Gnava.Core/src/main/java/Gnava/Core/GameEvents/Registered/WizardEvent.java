package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.AbstractGameEvent;
import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.GameEvents.Conditions.Universal.MinimumWorldSettlementsCountCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public final class WizardEvent extends AbstractGameEvent<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumWorldSettlementsCountCondition<>(1),
            new MinimumSettlementPopulationCondition(500)
        );
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.wizard.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.wizard.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        return Map.ofEntries(
            Map.entry("wizardName", "WIZARD_NAME"),
            Map.entry("name", context.getRandomTargetSettlement().getName())
        );
    }

    @Override
    public boolean firesOnce() {
        return true;
    }
}
