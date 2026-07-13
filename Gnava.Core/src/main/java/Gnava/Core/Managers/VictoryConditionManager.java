package Gnava.Core.Managers;

import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventBus.Events.GameOutcomeReceivedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public final class VictoryConditionManager extends AbstractGameManager {
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final ApplicationEventPublisher applicationEventPublisher;

    public VictoryConditionManager(
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider, ApplicationEventPublisher applicationEventPublisher
    ) {
        super(gameState);
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        if (worldStatisticsProvider.getWorldStatistics().population() < 1000 && gameState.getCurrentDay() >= 60) {
            applicationEventPublisher.publishEvent(new GameOutcomeReceivedEvent(GameOutcome.GAME_LOST));
        }
    }
}
