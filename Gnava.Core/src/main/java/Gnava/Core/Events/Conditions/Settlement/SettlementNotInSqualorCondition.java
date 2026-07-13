package Gnava.Core.Events.Conditions.Settlement;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;

public final class SettlementNotInSqualorCondition implements EventCondition<SettlementEventContext> {
    @Override
    public boolean isSatisfied(SettlementEventContext context) {
        return context.getRandomTargetSettlement().getWealthLevel() != SettlementWealthLevel.DESTITUTE;
    }
}
