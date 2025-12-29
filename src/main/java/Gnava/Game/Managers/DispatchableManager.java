package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;

public interface DispatchableManager<T> {
    EventDispatcher<T> getDispatcher();
}
