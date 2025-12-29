package Gnava.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventDispatcher<T> {
    private final List<Consumer<T>> listeners = new ArrayList<>();

    public EventDispatcher() { }

    public void addListener(Consumer<T> listener) {
        listeners.add(listener);
    }

    public void dispatch(T arg) {
        for (Consumer<T> listener : listeners) {
            listener.accept(arg);
        }
    }
}
