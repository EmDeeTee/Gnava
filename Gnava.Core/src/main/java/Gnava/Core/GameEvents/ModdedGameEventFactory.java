package Gnava.Core.GameEvents;

import Gnava.ModApi.IModdedGameEvent;
import Gnava.ModApi.IModdedGameEventFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public final class ModdedGameEventFactory implements IModdedGameEventFactory {
    private final EventRegistry eventRegistry;

    public ModdedGameEventFactory(EventRegistry eventRegistry) {
        this.eventRegistry = eventRegistry;
    }

    @Override
    public void register(Class<? extends IModdedGameEvent> gameEventType) {

    }

    @SuppressWarnings("unchecked")
    private Class<? extends AbstractGameEventDefinition<?>> asCoreEventType(
        Class<? extends IModdedGameEvent> gameEventType
    ) {
        Class<? extends IModdedGameEvent> eventType = Objects.requireNonNull(gameEventType, "gameEventType");

        if (!AbstractGameEventDefinition.class.isAssignableFrom(eventType)) {
            throw new IllegalArgumentException(
                "Mod game events must extend AbstractGameEventDefinition: " + eventType.getName()
            );
        }

        return (Class<? extends AbstractGameEventDefinition<?>>) eventType;
    }
}
