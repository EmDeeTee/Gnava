package Gnava.Core.GameEvents.Conditions.Settlement;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;

public final class SettlementNotInSqualorCondition implements EventCondition<SettlementEventContext> {
    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().getWealthLevel() != SettlementWealthLevel.DESTITUTE;
    }
}
