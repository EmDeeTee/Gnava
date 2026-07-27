package Gnava.Core.GameEvents.Conditions.Settlement;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;

public final class SettlementHasFreePopulationCapacityCondition implements EventCondition<SettlementEventContext> {
    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().getPopulationCapacityRemaining() > 0;
    }
}
