package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.ExecutedGameEventReceivedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameEvents.AbstractGameEvent;
import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.Core.GameEvents.Contexts.Providers.IEventContextProvider;
import Gnava.Core.GameEvents.EventRegistry;
import Gnava.Core.GameEvents.GameEventFactory;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Core.TimeState;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GameEventManagerTest {
    @Test
    void onTimeAdvanced_onlyRunsOnceOnlyEventOnceWhenRegistryCreatesFreshInstances() throws Exception {
        TimeState timeState = new TimeState();
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);
        EventRegistry registry = new EventRegistry(List.of(
            new GameEventFactory<>(TestEvent.class, TestEventContext.class)
        ));

        GameEventManager manager = new GameEventManager(
            timeState,
            publisher,
            registry,
            List.of(new TestContextProvider(timeState))
        );

        invokeOnTimeAdvanced(manager, 1);
        invokeOnTimeAdvanced(manager, 2);

        assertTrue(manager.hasEventHappened(TestEvent.class));
        verify(publisher, times(1)).publishEvent(any(ExecutedGameEventReceivedEvent.class));
    }

    private void invokeOnTimeAdvanced(GameEventManager manager, int day) throws Exception {
        Method method = GameEventManager.class.getDeclaredMethod("onTimeAdvanced", TimeAdvancedEvent.class);
        method.setAccessible(true);
        method.invoke(manager, new TimeAdvancedEvent(day));
    }

    private static final class TestContextProvider implements IEventContextProvider<TestEventContext> {
        private final TimeState timeState;

        private TestContextProvider(TimeState timeState) {
            this.timeState = timeState;
        }

        @Override
        public TestEventContext buildContext() {
            return new TestEventContext(timeState);
        }
    }

    private static final class TestEventContext extends EventContext {
        private TestEventContext(TimeState timeState) {
            super(timeState, mock(WorldStatisticsProvider.class));
        }
    }

    private static final class TestEvent extends AbstractGameEvent<TestEventContext> {
        @Override
        protected String getTitleTranslationKey() {
            return "test.title";
        }

        @Override
        protected String getDescriptionTranslationKey() {
            return "test.description";
        }

        @Override
        public boolean firesOnce() {
            return true;
        }
    }
}
