package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;

import java.util.HashMap;
import java.util.Map;

// TODO: This is probably a better choice? It avoids forcing only one event dispatcher per manager
public abstract class GameManager {
    private final Map<Class<?>, EventDispatcher<?>> dispatchers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> EventDispatcher<T> getDispatcher(Class<T> type) {
        return (EventDispatcher<T>) dispatchers.computeIfAbsent(type, k -> new EventDispatcher<>());
    }
}
