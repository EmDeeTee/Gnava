package Gnava.Core.Events;

import Gnava.Core.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class EventContext {
    private final Object subject;
    private final GameState gameState;
    private final Map<String, Object> attachments = new HashMap<>();

    public EventContext(Object subject, GameState gameState) {
        this.subject = subject;
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

    public Object getSubject() {
        return subject;
    }

    public GameState getGameState() {
        return gameState;
    }
}
