package Gnava.Core.Events.Conditions.Settlement;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;

public final class SettlementPopulationTypeCondition implements EventCondition<SettlementEventContext> {
    private final SettlementPopulationType target;

    public SettlementPopulationTypeCondition(SettlementPopulationType target) {
        this.target = target;
    }

    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().orElseThrow().getPopulationType() == target;
    }
}
