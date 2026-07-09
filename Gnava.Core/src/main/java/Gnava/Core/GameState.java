package Gnava.Core;

import org.springframework.stereotype.Component;

// NOTE: Actually, why does this class even exist?
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
