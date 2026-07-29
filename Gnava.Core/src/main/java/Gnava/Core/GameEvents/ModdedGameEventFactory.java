package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.ModApi.GameEvents.IModdedGameEvent;
import org.springframework.stereotype.Service;

@Service
public final class ModdedGameEventFactory implements IGameEventFactory {
    private final Class<? extends IModdedGameEvent> eventType;

    public ModdedGameEventFactory(Class<? extends IModdedGameEvent> eventType) {
        this.eventType = eventType;
    }

    @Override
    public IGameEventDefinition<EventContext> create() {
        try {
            IModdedGameEvent event = eventType.getDeclaredConstructor().newInstance();

            return new ModdedGameEventAdapter(event);

        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                "Failed to create mod event " + eventType.getName(),
                e
            );
        }
    }

    @Override
    public Class<EventContext> contextType() {
        return EventContext.class;
    }
}