package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;

public class TimeManager implements DispatchableManager<Integer> {
    private Integer currentDay = 0;
    private final EventDispatcher<Integer> dispatcher = new EventDispatcher<>();

    public void advanceTime() {
        currentDay++;
        dispatcher.dispatch(currentDay);
    }

    @Override
    public EventDispatcher<Integer> getDispatcher() {
        return dispatcher;
    }
}
