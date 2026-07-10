package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public final class TimeManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;

    public TimeManager(GameState gameState, ApplicationEventPublisher applicationEventPublisher) {
        super(gameState);
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void advanceTime() {
        int nextDay = gameState.getCurrentDay() + 1;

        gameState.setCurrentDay(nextDay);
        applicationEventPublisher.publishEvent(new TimeAdvancedEvent(nextDay));
    }
}
