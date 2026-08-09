package Gnava.GameApi;

@FunctionalInterface
public interface IGameDayListener {
    void onDayAdvanced(int currentDay);
}
