package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;

public interface IGameEventFactory<T extends EventContext> {
    Class<T> contextType();
    IGameEventDefinition<T> create();
}
