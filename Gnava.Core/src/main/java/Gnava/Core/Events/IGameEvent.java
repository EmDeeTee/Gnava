package Gnava.Core.Events;

public interface IGameEvent {
    GameEvent happen(EventContext context);

    boolean canRun(EventContext context);

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();
}
