package Gnava.Game.Managers;

import java.util.function.Consumer;

public class TimeManager extends DispatchableManager<Integer> {
    private Integer currentDay = 0;

    public void advanceTime() {
        currentDay++;
        dispatcher.dispatch(currentDay);
    }

    public void addTimeAdvancedListener(Consumer<Integer> listener) {
        getDispatcher().addListener(listener);
    }
}
