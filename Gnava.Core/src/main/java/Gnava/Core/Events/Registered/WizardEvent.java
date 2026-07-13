package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumWorldSettlementsCountCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class WizardEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumWorldSettlementsCountCondition<>(1),
            new MinimumSettlementPopulationCondition(500)
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        // TODO: Add wizard names to names generator
        return "A local wizard, %s, discovered ancient ruins in %s. Dozens flock to the settlement to see".formatted(
            "WIZARD_NAME",
            context.getRandomTargetSettlement().getName()
        );
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Local wizard discovers ancient ruins";
    }

    @Override
    public boolean firesOnce() {
        return true;
    }
}
