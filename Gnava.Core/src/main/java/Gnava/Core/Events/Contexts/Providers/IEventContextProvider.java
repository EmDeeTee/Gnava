package Gnava.Core.Events.Contexts.Providers;

import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.IGameEventDefinition;

import java.util.List;

public interface IEventContextProvider<T extends EventContext> {
    T buildContext();
    List<IGameEventDefinition<T>> getEvents();
}
