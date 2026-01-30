package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.GameState;
import Gnava.Game.Managers.Listeners.GameDayListener;

public class TimeManager extends GameManager {
    private final EventDispatcher<Integer> timeAdvancedDispatcher = new EventDispatcher<>();
    private Integer currentDay = 0;

    public TimeManager(GameState gameState) {
        super(gameState);
    }

    public void advanceTime() {
        currentDay++;
        timeAdvancedDispatcher.dispatch(currentDay);
    }

    public void addTimeAdvancedListener(GameDayListener listener) {
        timeAdvancedDispatcher.addListener(listener::onGameDayAdvanced);
    }

    public Integer getCurrentDay() {
        return currentDay;
    }
}
