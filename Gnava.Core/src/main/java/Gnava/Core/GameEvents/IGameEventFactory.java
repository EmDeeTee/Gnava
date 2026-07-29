package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;

public interface IGameEventFactory {
    IGameEventDefinition<? extends EventContext> create();
    Class<? extends EventContext> contextType();
}
