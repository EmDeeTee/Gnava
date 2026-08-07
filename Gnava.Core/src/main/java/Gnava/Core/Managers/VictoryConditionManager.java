package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.GameEvents.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.GameEvents.GameEventEngine;
import Gnava.Core.GameEvents.Registered.KEvent3;
import Gnava.Core.TimeState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public final class VictoryConditionManager extends AbstractGameManager {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameEventEngine gameEventEngine;

    public VictoryConditionManager(
        TimeState timeState,
        ApplicationEventPublisher applicationEventPublisher,
        GameEventEngine gameEventEngine
    ) {
        super(timeState);
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameEventEngine = gameEventEngine;
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        if (gameEventEngine.hasEventHappened(KEvent3.ID)) {
            applicationEventPublisher.publishEvent(new GameOutcomeReceivedEvent(GameOutcome.GAME_ENDED));
        }
    }
}
