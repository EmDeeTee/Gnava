package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.Events.Registered.KEvent2;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public final class VictoryConditionManager extends AbstractGameManager {
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final GameEventManager gameEventManager;

    public VictoryConditionManager(
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider,
        ApplicationEventPublisher applicationEventPublisher,
        GameEventManager gameEventManager
    ) {
        super(gameState);
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.applicationEventPublisher = applicationEventPublisher;
        this.gameEventManager = gameEventManager;
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        if (gameEventManager.hasEventHappened(KEvent2.class)) {
            applicationEventPublisher.publishEvent(new GameOutcomeReceivedEvent(GameOutcome.GAME_ENDED));
        }
    }
}
