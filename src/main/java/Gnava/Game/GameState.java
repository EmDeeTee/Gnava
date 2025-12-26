package Gnava.Game;

import Gnava.Game.Managers.SettlementManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameState {
    private static GameState instance = null;
    private final SettlementManager settlementManager = new SettlementManager();

    private Integer currentDay = 0;

    private final List<Consumer<Integer>> timeListeners = new ArrayList<>();

    private GameState() { }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }

    public void advanceTime() {
        currentDay++;
        notifyTimeListeners();
    }

    public void addTimeListener(Consumer<Integer> listener) {
        timeListeners.add(listener);
    }

    private void notifyTimeListeners() {
        for (Consumer<Integer> listener : timeListeners) {
            listener.accept(currentDay);
        }
    }
}
