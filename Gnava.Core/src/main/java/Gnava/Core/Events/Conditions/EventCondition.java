package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.EventContext;

@FunctionalInterface
public interface EventCondition {
     boolean isSatisfied(EventContext eventContext);
}
