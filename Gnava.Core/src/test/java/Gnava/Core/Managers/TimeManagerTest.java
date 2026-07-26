package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Mod.Context.GameTimeApi;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TimeManagerTest {
    @Test
    void advanceTime_publishesTheNewDayToCoreAndModListeners() {
        GameState gameState = new GameState();
        ApplicationEventPublisher applicationEvents = mock(ApplicationEventPublisher.class);
        GameTimeApi modTime = new GameTimeApi();
        AtomicInteger receivedDay = new AtomicInteger();
        modTime.onDayAdvanced(receivedDay::set);
        TimeManager timeManager = new TimeManager(gameState, applicationEvents, modTime);

        timeManager.advanceTime();

        assertEquals(1, gameState.getCurrentDay());
        assertEquals(1, receivedDay.get());
        verify(applicationEvents).publishEvent(new TimeAdvancedEvent(1));
    }
}
