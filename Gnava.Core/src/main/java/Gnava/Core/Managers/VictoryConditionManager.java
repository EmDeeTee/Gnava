package Gnava.Core.Managers;

import Gnava.Core.Events.Enums.GameOutcome;
import Gnava.Core.EventDispatcher;
import Gnava.Core.Events.GameOutcomeReceivedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Managers.Listeners.GameOutcomeListener;
import org.jetbrains.annotations.NotNull;

public class VictoryConditionManager extends GameManager {
    EventDispatcher<GameOutcomeReceivedEvent> gameOutcomeSetEventDispatcher = new EventDispatcher<>();

    public VictoryConditionManager(GameState gameState) {
        super(gameState);
        gameState.getTimeManager().addTimeAdvancedListener(this::onTimeAdvanced);
    }

    public void addGameOutcomeListener(@NotNull GameOutcomeListener listener) {
        gameOutcomeSetEventDispatcher.addListener(listener::onGameEnded);
    }

    private void onTimeAdvanced(int currentDay) {
        if (gameState.getWorldStatistics().population() < 1000 && gameState.getTimeManager().getCurrentDay() >= 60) {
            gameOutcomeSetEventDispatcher.dispatch(new GameOutcomeReceivedEvent(GameOutcome.GAME_LOST));
        }
    }
}
