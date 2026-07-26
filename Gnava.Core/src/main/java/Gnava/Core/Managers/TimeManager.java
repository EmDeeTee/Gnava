package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Mod.Context.GameTimeApi;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public final class TimeManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameTimeApi gameTimeApi;

    public TimeManager(
        GameState gameState,
        ApplicationEventPublisher applicationEventPublisher,
        GameTimeApi gameTimeApi
    ) {
        super(gameState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameTimeApi = gameTimeApi;
    }

    public void advanceTime() {
        int nextDay = gameState.getCurrentDay() + 1;

        gameState.setCurrentDay(nextDay);
        applicationEventPublisher.publishEvent(new TimeAdvancedEvent(nextDay));
        gameTimeApi.publishDayAdvanced(nextDay);
    }
}
