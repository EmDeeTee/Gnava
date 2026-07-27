package Gnava.Core.GameEvents;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public final class EventRegistry {
    private final List<AbstractGameEventDefinition<?>> gameEventDefinitions = new ArrayList<>();
    private final GameEventFactory gameEventFactory = new GameEventFactory();

    public EventRegistry() {
        registerCoreGameEvents();
    }

    public void register(AbstractGameEventDefinition<?> gameEventDefinition) {
        gameEventDefinitions.add(Objects.requireNonNull(gameEventDefinition, "gameEventDefinition"));
    }

    public <T extends AbstractGameEventDefinition<?>> void register(Class<T> gameEventType) {
        register(gameEventFactory.create(gameEventType));
    }

    public List<AbstractGameEventDefinition<?>> getRegisteredEvents() {
        return List.copyOf(gameEventDefinitions);
    }

    private void registerCoreGameEvents() {
        register(TestEvent.class);
    }

    @EventListener
    private void tmp(TimeAdvancedEvent e) {
        AbstractGameEventDefinition<?> g = getRegisteredEvents().stream().filter(f -> f.getClass() == TestEvent.class).findFirst().get();
        System.out.println(g.getTitleTranslationKey());
    }
}
