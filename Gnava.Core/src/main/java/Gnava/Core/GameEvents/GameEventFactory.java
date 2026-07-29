package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;

public final class GameEventFactory<T extends AbstractGameEvent<C>, C extends EventContext> implements IGameEventFactory {
    private final Class<T> eventType;
    private final Class<C> contextType;

    public GameEventFactory(Class<T> eventType, Class<C> contextType) {
        this.eventType = eventType;
        this.contextType = contextType;
    }

    @Override
    public IGameEventDefinition<C> create() {
        try {
            return eventType.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                    "Failed to create event " + eventType.getName(),
                    e
            );
        }
    }

    @Override
    public Class<C> contextType() {
        return contextType;
    }
}