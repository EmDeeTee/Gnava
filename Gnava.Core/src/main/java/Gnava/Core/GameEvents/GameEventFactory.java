package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;

public final class GameEventFactory implements IGameEventFactory {
    @Override
    public <T extends AbstractGameEventDefinition<? extends EventContext>> T create(Class<T> gameEventType) {
        try {
            return gameEventType.getConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new IllegalArgumentException(
                "Game events must have a public no-argument constructor: " + gameEventType.getName(),
                exception
            );
        }
    }
}
