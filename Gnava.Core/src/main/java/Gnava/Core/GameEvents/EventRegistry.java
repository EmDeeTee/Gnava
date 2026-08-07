package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.GameEvents.Contexts.WorldEventContext;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class EventRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventRegistry.class);

    private final Map<GameEventId, IGameEvent<?>> events = new LinkedHashMap<>();
    private final List<IGameEvent<WorldEventContext>> worldEvents = new ArrayList<>();
    private final List<IGameEvent<SettlementEventContext>> settlementEvents = new ArrayList<>();

    public synchronized void register(IGameEvent<?> event) {
        IGameEvent<?> previous = events.putIfAbsent(event.specification().id(), event);
        if (previous != null) {
            throw new IllegalArgumentException("Duplicate game event id: " + event.specification().id());
        }

        if (event.specification().scope() == GameEventScope.WORLD) {
            worldEvents.add(asWorldEvent(event));
        } else {
            settlementEvents.add(asSettlementEvent(event));
        }

        LOGGER.debug("Registered new game event: {}", event.specification().id());
    }

    public synchronized Optional<IGameEvent<?>> find(GameEventId id) {
        return Optional.ofNullable(events.get(id));
    }

    public synchronized List<IGameEvent<?>> allEvents() {
        return List.copyOf(events.values());
    }

    public synchronized List<IGameEvent<WorldEventContext>> worldEvents() {
        return List.copyOf(worldEvents);
    }

    public synchronized List<IGameEvent<SettlementEventContext>> settlementEvents() {
        return List.copyOf(settlementEvents);
    }

    @SuppressWarnings("unchecked")
    private IGameEvent<WorldEventContext> asWorldEvent(IGameEvent<?> event) {
        return (IGameEvent<WorldEventContext>) event;
    }

    @SuppressWarnings("unchecked")
    private IGameEvent<SettlementEventContext> asSettlementEvent(IGameEvent<?> event) {
        return (IGameEvent<SettlementEventContext>) event;
    }
}
