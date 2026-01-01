package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;

import java.util.function.Consumer;

public class TimeManager {
    private final EventDispatcher<Integer> timeAdvancedDispatcher = new EventDispatcher<>();
    private Integer currentDay = 0;

    public void advanceTime() {
        currentDay++;
        timeAdvancedDispatcher.dispatch(currentDay);
    }

    public void addTimeAdvancedListener(Consumer<Integer> listener) {
        timeAdvancedDispatcher.addListener(listener);
    }
}
