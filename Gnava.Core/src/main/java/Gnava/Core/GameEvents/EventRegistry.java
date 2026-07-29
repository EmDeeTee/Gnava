package Gnava.Core.GameEvents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public final class EventRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventRegistry.class);
    private final List<IGameEventFactory> factories = new ArrayList<>();

    public void register(IGameEventFactory factory) {
        factories.add(factory);
        LOGGER.debug("Registered event factory {}", factory);
    }

    public List<IGameEventFactory> getAllRegisteredEvents() {
        return List.copyOf(factories);
    }
}
