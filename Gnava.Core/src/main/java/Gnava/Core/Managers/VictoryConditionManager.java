package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameEvents.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.GameEvents.Registered.KEvent3;
import Gnava.Core.TimeState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public final class VictoryConditionManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameEventManager gameEventManager;

    public VictoryConditionManager(
        TimeState timeState,
        ApplicationEventPublisher applicationEventPublisher,
        GameEventManager gameEventManager
    ) {
        super(timeState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameEventManager = gameEventManager;
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        if (gameEventManager.hasEventHappened(KEvent3.class)) {
            applicationEventPublisher.publishEvent(new GameOutcomeReceivedEvent(GameOutcome.GAME_ENDED));
        }
    }
}
