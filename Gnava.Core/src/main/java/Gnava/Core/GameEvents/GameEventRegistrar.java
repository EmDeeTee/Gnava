package Gnava.Core.GameEvents;

import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.IGameEventRegistrar;
import org.springframework.stereotype.Service;

@Service
public final class GameEventRegistrar implements IGameEventRegistrar {
    private final EventRegistry eventRegistry;

    public GameEventRegistrar(EventRegistry eventRegistry) {
        this.eventRegistry = eventRegistry;
    }

    @Override
    public void register(IGameEvent<?> IGameEvent) {
        eventRegistry.register(IGameEvent);
    }
}
