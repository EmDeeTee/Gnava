package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.WorldEventContext;

public final class MinimumWorldSettlementsCondition implements EventCondition<WorldEventContext> {
    private final int minimumSettlements;

    public MinimumWorldSettlementsCondition(int minimumSettlements) {
        this.minimumSettlements = minimumSettlements;
    }

    @Override
    public boolean isSatisfied(WorldEventContext eventContext) {
        return eventContext.getStatistics().settlementCount() >= minimumSettlements;
    }
}
