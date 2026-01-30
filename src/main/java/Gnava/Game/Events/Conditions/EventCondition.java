package Gnava.Game.Events.Conditions;

import Gnava.Game.Events.EventContext;

@FunctionalInterface
public interface EventCondition {
     boolean isSatisfied(EventContext eventContext);
}
