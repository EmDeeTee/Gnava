package Gnava.Game.Events;

public interface GameEventDefinition {
    GameEvent happen(EventContext context);

    boolean canRun(EventContext context);

    boolean firesOnce();

    float probability();

    boolean isStoryEvent();
}
