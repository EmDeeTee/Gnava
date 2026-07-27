package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

class GameEventFactoryTest {
    @Test
    void create_returnsANewEventInstance() {
        GameEventFactory factory = new GameEventFactory();

        TestEvent firstEvent = factory.create(TestEvent.class);
        TestEvent secondEvent = factory.create(TestEvent.class);

        assertSame(TestEvent.class, firstEvent.getClass());
        assertNotSame(firstEvent, secondEvent);
    }

    public static final class TestEvent extends AbstractGameEventDefinition<EventContext> {
        @Override
        protected String getTitleTranslationKey() {
            return "test.title";
        }

        @Override
        protected String getDescriptionTranslationKey() {
            return "test.description";
        }
    }
}
