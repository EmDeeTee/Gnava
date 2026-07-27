package Gnava.Core.GameEvents.Conditions.Universal;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.EventContext;

public final class MinimumWorldSettlementsCountCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumSettlements;

    public MinimumWorldSettlementsCountCondition(int minimumSettlements) {
        this.minimumSettlements = minimumSettlements;
    }

    @Override
    public boolean isSatisfied(C context) {
        return context.getWorldStatistics().settlementCount() >= minimumSettlements;
    }
}
