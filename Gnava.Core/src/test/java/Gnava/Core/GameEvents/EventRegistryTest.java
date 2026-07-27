package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Registered.KEvent;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class EventRegistryTest {
    @Test
    void register_returnsRegisteredEventsInRegistrationOrder() {
        EventRegistry registry = new EventRegistry();
        IGameEventDefinition<?> firstEvent = mock(IGameEventDefinition.class);
        IGameEventDefinition<?> secondEvent = mock(IGameEventDefinition.class);

        registry.register(firstEvent);
        registry.register(secondEvent);

        assertEquals(List.of(firstEvent, secondEvent), registry.getRegisteredEvents());
    }

    @Test
    void register_rejectsNullEvents() {
        EventRegistry registry = new EventRegistry();

        assertThrows(NullPointerException.class, () -> registry.register((IGameEventDefinition<?>) null));
    }

    @Test
    void register_createsAndRegistersAClassBasedEvent() {
        EventRegistry registry = new EventRegistry();

        registry.register(KEvent.class);

        assertEquals(1, registry.getRegisteredEvents().size());
        assertEquals(KEvent.class, registry.getRegisteredEvents().getFirst().getClass());
    }
}
