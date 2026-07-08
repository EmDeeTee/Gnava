package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.EventContext;

@FunctionalInterface
public interface EventCondition<C extends EventContext> {
     boolean isSatisfied(C eventContext);
}
