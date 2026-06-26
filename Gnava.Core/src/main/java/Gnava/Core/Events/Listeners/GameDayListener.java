package Gnava.Core.Events.Listeners;

@FunctionalInterface
public interface GameDayListener {
    void onGameDayAdvanced(int currentDay);
}
