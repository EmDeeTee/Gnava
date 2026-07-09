package Gnava.Core;

import org.springframework.stereotype.Component;

// NOTE: Actually, why does this class even exist?
// Maybe turn this class into a GameClock of sorts?
// So it will own the current time, and then mutate it in the TimeManager?
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
