package Gnava.Core.Events;

import Gnava.Core.Events.Contexts.EventContext;

import java.util.List;

// TODO: I think more methods from AbstractGameEvent belong here
public interface IGameEventDefinition<C extends EventContext> {
    ExecutedGameEvent happen(C context);

    boolean canRun(C context);

    List<Class<? extends IGameEventDefinition<?>>>  prerequisites();

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();

    boolean isMinor();
}
