package Gnava.ModApi;

@FunctionalInterface
public interface IGameDayListener {
    void onDayAdvanced(int currentDay);
}
