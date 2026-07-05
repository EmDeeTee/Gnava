package Gnava.Core.Managers;

import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventDispatcher;
import Gnava.Core.Events.GameOutcomeReceivedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Events.Listeners.GameOutcomeListener;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class VictoryConditionManager extends AbstractGameManager {
    EventDispatcher<GameOutcomeReceivedEvent> gameOutcomeSetEventDispatcher = new EventDispatcher<>();
    private final WorldStatisticsProvider worldStatisticsProvider;

    public VictoryConditionManager(
        GameState gameState,
        TimeManager timeManager,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super(gameState);
        this.worldStatisticsProvider = worldStatisticsProvider;
        timeManager.addTimeAdvancedListener(this::onTimeAdvanced);
    }

    public void addGameOutcomeListener(@NotNull GameOutcomeListener listener) {
        gameOutcomeSetEventDispatcher.addListener(listener::onGameEnded);
    }

    private void onTimeAdvanced(int currentDay) {
        if (worldStatisticsProvider.getWorldStatistics().population() < 1000 && gameState.getCurrentDay() >= 60) {
            gameOutcomeSetEventDispatcher.dispatch(new GameOutcomeReceivedEvent(GameOutcome.GAME_LOST));
        }
    }
}
