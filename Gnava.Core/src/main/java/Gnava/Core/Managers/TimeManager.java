package Gnava.Core.Managers;

import Gnava.Core.EventDispatcher;
import Gnava.Core.GameState;
import Gnava.Core.Events.Listeners.GameDayListener;
import org.springframework.stereotype.Service;

@Service
public class TimeManager extends AbstractGameManager {
    private final EventDispatcher<Integer> timeAdvancedDispatcher = new EventDispatcher<>();

    public TimeManager(GameState gameState) {
        super(gameState);
    }

    public void advanceTime() {
        int nextDay = gameState.getCurrentDay() + 1;

        gameState.setCurrentDay(nextDay);
        timeAdvancedDispatcher.dispatch(nextDay);
    }

    public void addTimeAdvancedListener(GameDayListener listener) {
        timeAdvancedDispatcher.addListener(listener::onGameDayAdvanced);
    }
}
