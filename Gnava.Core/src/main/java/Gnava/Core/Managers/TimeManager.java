package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameEvents.GameEventEngine;
import Gnava.Core.TimeState;
import Gnava.Core.Mod.Context.GameTimeApi;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public final class TimeManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameTimeApi gameTimeApi;
    private final GameEventEngine gameEventEngine;

    public TimeManager(
        TimeState timeState,
        ApplicationEventPublisher applicationEventPublisher,
        GameTimeApi gameTimeApi,
        GameEventEngine gameEventEngine
    ) {
        super(timeState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameTimeApi = gameTimeApi;
        this.gameEventEngine = gameEventEngine;
    }

    public void advanceTime() {
        int nextDay = timeState.getCurrentDay() + 1;

        timeState.setCurrentDay(nextDay);
        gameEventEngine.runDay();
        applicationEventPublisher.publishEvent(new TimeAdvancedEvent(nextDay));
        gameTimeApi.publishDayAdvanced(nextDay);
    }
}
