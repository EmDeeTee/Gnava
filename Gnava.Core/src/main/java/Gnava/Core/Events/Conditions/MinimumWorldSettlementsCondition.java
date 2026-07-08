package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.EventContext;

public final class MinimumWorldSettlementsCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumSettlements;

    public MinimumWorldSettlementsCondition(int minimumSettlements) {
        this.minimumSettlements = minimumSettlements;
    }

    @Override
    public boolean isSatisfied(C eventContext) {
        return eventContext.getWorldStatistics().settlementCount() >= minimumSettlements;
    }
}
