package Gnava.Core.GameEvents.Conditions.Settlement;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;

public final class SettlementPopulationTypeCondition implements EventCondition<SettlementEventContext> {
    private final SettlementPopulationType target;

    public SettlementPopulationTypeCondition(SettlementPopulationType target) {
        this.target = target;
    }

    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().getPopulationType() == target;
    }
}
