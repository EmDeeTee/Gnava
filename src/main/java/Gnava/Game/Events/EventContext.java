package Gnava.Game.Events;

import Gnava.Game.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class EventContext {
    private final Object subject;
    private final GameState gameState;
    private final Map<Class<?>, Object> attachments = new HashMap<>();

    public EventContext(Object subject, GameState gameState) {
        this.subject = subject;
        this.gameState = gameState;
    }

    public <T> void set(Class<T> clas, T object) {
        attachments.put(clas, object);
    }

    public <T> Optional<T> get(Class<T> clas) {
        Object object = attachments.get(clas);
        return Optional.ofNullable(clas.cast(object));
    }

    public Object getSubject() {
        return subject;
    }

    public GameState getGameState() {
        return gameState;
    }
}
