package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.EventContext;

@FunctionalInterface
public interface EventCondition {
     boolean isSatisfied(EventContext eventContext);
}
