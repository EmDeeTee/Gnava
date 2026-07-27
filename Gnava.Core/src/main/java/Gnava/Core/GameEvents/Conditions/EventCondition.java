package Gnava.Core.GameEvents.Conditions;

import Gnava.Core.GameEvents.Contexts.EventContext;

@FunctionalInterface
public interface EventCondition<C extends EventContext> {
     boolean isSatisfied(C context);
}
