package Gnava.Core;

import org.springframework.stereotype.Component;

@Component
public class GameState {
    private int currentDay = 0;

    public GameState() { }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int day) {
        this.currentDay = day;
    }
}
