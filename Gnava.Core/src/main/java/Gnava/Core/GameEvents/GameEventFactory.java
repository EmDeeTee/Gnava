package Gnava.Core.GameEvents;

import java.util.Objects;

public final class GameEventFactory {
    public <T extends IGameEventDefinition<?>> T create(Class<T> gameEventType) {
        Class<T> eventType = Objects.requireNonNull(gameEventType, "gameEventType");

        try {
            return eventType.getConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new IllegalArgumentException(
                "Game events must have a public no-argument constructor: " + eventType.getName(),
                exception
            );
        }
    }
}
