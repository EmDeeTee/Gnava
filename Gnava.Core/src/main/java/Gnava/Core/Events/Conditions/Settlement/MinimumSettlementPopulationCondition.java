package Gnava.Core.Events.Conditions.Settlement;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;

public final class MinimumSettlementPopulationCondition implements EventCondition<SettlementEventContext> {
    private final int minimum;

    public MinimumSettlementPopulationCondition(int minimum) {
        this.minimum = minimum;
    }

    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().orElseThrow().getTotalPopulation() > minimum;
    }
}
