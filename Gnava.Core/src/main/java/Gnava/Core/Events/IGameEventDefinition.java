package Gnava.Core.Events;

import Gnava.Core.Events.Contexts.EventContext;

// TODO: I think more methods from AbstractGameEvent belong here
public interface IGameEventDefinition<C extends EventContext> {
    ExecutedGameEvent happen(C context);

    boolean canRun(C context);

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();

    boolean isMinor();
}
