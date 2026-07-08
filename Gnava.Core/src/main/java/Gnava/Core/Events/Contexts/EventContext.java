package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class EventContext {
    private final GameState gameState;
    private final Map<String, Object> attachments = new HashMap<>();

    public EventContext(
        GameState gameState
    ) {
        this.gameState = gameState;
    }

    public <T> void set(String id, T object) {
        attachments.put(id, object);
    }

    public <T> Optional<T> get(String id, Class<T> type) {
        Object object = attachments.get(id);

        if (type.isInstance(object)) {
            return Optional.of(type.cast(object));
        }

        return Optional.empty();
    }

    public GameState getGameState() {
        return gameState;
    }
}
