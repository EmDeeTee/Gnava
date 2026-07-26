package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.TimeState;
import Gnava.Core.Mod.Context.GameTimeApi;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public final class TimeManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameTimeApi gameTimeApi;

    public TimeManager(
        TimeState timeState,
        ApplicationEventPublisher applicationEventPublisher,
        GameTimeApi gameTimeApi
    ) {
        super(timeState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameTimeApi = gameTimeApi;
    }

    public void advanceTime() {
        int nextDay = timeState.getCurrentDay() + 1;

        timeState.setCurrentDay(nextDay);
        applicationEventPublisher.publishEvent(new TimeAdvancedEvent(nextDay));
        gameTimeApi.publishDayAdvanced(nextDay);
    }
}
