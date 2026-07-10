package Gnava.Core.Events.Conditions.Settlement;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Settlement.Enums.SettlementWealthLevel;

public final class SettlementNotInSqualorCondition implements EventCondition<SettlementEventContext> {
    @Override
    public boolean isSatisfied(SettlementEventContext eventContext) {
        return eventContext.getRandomTargetSettlement().orElseThrow().getWealthLevel() != SettlementWealthLevel.DESTITUTE;
    }
}
