package Gnava.Core.Managers.Listeners;

@FunctionalInterface
public interface GameDayListener {
    void onGameDayAdvanced(int currentDay);
}
