package Gnava.Core.Events.Conditions.Settlement;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;

public final class SettlementHasFreePopulationCapacityCondition implements EventCondition<SettlementEventContext> {
    @Override
    public boolean isSatisfied(SettlementEventContext eventContext) {
        return eventContext.getRandomTargetSettlement().orElseThrow().getPopulationCapacityRemaining() > 0;
    }
}
