package Gnava.Core.GameEvents.Contexts.Providers;

import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.Core.GameEvents.IGameEventDefinition;

import java.util.List;

public interface IEventContextProvider<T extends EventContext> {
    T buildContext();
    List<IGameEventDefinition<T>> getEvents();
}
