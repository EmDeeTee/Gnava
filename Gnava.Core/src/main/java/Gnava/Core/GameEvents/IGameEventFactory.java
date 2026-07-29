package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;

public interface IGameEventFactory {
    <T extends AbstractGameEventDefinition<? extends EventContext>> T create(Class<T> gameEventType);
}
