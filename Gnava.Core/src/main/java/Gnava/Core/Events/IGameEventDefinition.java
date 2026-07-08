package Gnava.Core.Events;

import Gnava.Core.Events.Contexts.EventContext;

public interface IGameEventDefinition<C extends EventContext> {
    ExecutedGameEvent happen(C context);

    boolean canRun(C context);

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();

    boolean isMinor();
}
