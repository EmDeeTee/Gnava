package Gnava.GameApi.GameEvents;

import java.util.random.RandomGenerator;

public interface IGameEvent<C extends IGameEventContext> {
    EventSpecification specification();

    default boolean canTrigger(C context) {
        return true;
    }

    default GameEventResult trigger(C context, RandomGenerator random) {
        return GameEventResult.empty();
    }
}
