package Gnava.Game.Managers.Listeners;

@FunctionalInterface
public interface GameDayListener {
    void onGameDayAdvanced(int currentDay);
}
