package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;

public abstract class DispatchableManager<T> {
    protected final EventDispatcher<T> dispatcher = new EventDispatcher<>();

    protected EventDispatcher<T> getDispatcher() {
        return dispatcher;
    }
}
