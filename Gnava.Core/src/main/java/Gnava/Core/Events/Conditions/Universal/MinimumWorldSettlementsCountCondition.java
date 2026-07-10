package Gnava.Core.Events.Conditions.Universal;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

public final class MinimumWorldSettlementsCountCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumSettlements;

    public MinimumWorldSettlementsCountCondition(int minimumSettlements) {
        this.minimumSettlements = minimumSettlements;
    }

    @Override
    public boolean isSatisfied(C eventContext) {
        return eventContext.getWorldStatistics().settlementCount() >= minimumSettlements;
    }
}
