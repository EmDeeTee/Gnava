package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.ModApi.GameEvents.IModdedGameEvent;
import org.springframework.stereotype.Service;

@Service
public final class ModdedGameEventFactory<T extends EventContext> implements IGameEventFactory<T> {
    private final Class<? extends IModdedGameEvent> eventType;
    private final Class<T> contextType;

    public ModdedGameEventFactory(Class<? extends IModdedGameEvent> eventType, Class<T> contextType) {
        this.eventType = eventType;
        this.contextType = contextType;
    }

    @Override
    public IGameEventDefinition<T> create() {
        try {
            IModdedGameEvent<T> event = eventType.getDeclaredConstructor().newInstance();

            return new ModdedGameEventAdapter<T>(event);

        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                "Failed to create mod event " + eventType.getName(),
                e
            );
        }
    }

    @Override
    public Class<T> contextType() {
        return contextType;
    }
}