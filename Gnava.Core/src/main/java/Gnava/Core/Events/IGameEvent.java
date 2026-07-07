package Gnava.Core.Events;

import Gnava.Core.Events.Contexts.EventContext;

public interface IGameEvent {
    ExecutedGameEvent happen(EventContext context);

    boolean canRun(EventContext context);

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();

    boolean isMinor();
}
