package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class EventRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventRegistry.class);

    private final List<IGameEventFactory<? extends EventContext>> factories = new ArrayList<>();

    public EventRegistry(List<IGameEventFactory<? extends EventContext>> initialFactories) {
        initialFactories.forEach(this::register);
    }

    public void register(IGameEventFactory<? extends EventContext> factory) {
        factories.add(factory);

        LOGGER.debug(
            "Registered event factory {}",
            factory.getClass().getName()
        );
    }

    public <T extends EventContext> List<IGameEventDefinition<T>> getEventsForContext(Class<T> contextType) {
        return factories.stream()
            .filter(factory ->
                factory.contextType().isAssignableFrom(contextType)
            )
            .map(IGameEventFactory::create)
            .map(this::<T>cast)
            .toList();
    }

    @SuppressWarnings("unchecked")
    private <T extends EventContext> IGameEventDefinition<T> cast(IGameEventDefinition<?> event) {
        return (IGameEventDefinition<T>) event;
    }
}