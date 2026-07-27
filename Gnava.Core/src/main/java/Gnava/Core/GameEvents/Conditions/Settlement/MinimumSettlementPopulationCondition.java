package Gnava.Core.GameEvents.Conditions.Settlement;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;

public final class MinimumSettlementPopulationCondition implements EventCondition<SettlementEventContext> {
    private final int minimum;

    public MinimumSettlementPopulationCondition(int minimum) {
        this.minimum = minimum;
    }

    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().getTotalPopulation() > minimum;
    }
}
