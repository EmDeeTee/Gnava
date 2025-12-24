package Gnava.Game;

import Gnava.Game.Settlements.Settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameState {
    private static GameState instance = null;

    private Integer currentDay = 0;
    private final List<Settlement> settlements = new ArrayList<>();

    private final List<Consumer<List<Settlement>>> settlementListeners = new ArrayList<>();
    private final List<Consumer<Integer>> timeListeners = new ArrayList<>();

    private GameState() { }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void advanceTime() {
        currentDay++;
        notifyTimeListeners();
    }

    public void addSettlement(Settlement settlement) {
        this.settlements.add(settlement);
        notifySettlementListeners();
    }

    public List<Settlement> getSettlements() {
        return List.copyOf(this.settlements);
    }

    public void addSettlementListener(Consumer<List<Settlement>> listener) {
        settlementListeners.add(listener);
    }

    public void addTimeListener(Consumer<Integer> listener) {
        timeListeners.add(listener);
    }

    private void notifySettlementListeners() {
        List<Settlement> snapshot = List.copyOf(this.settlements);
        for (Consumer<List<Settlement>> listener : settlementListeners) {
            listener.accept(snapshot);
        }
    }

    private void notifyTimeListeners() {
        for (Consumer<Integer> listener : timeListeners) {
            listener.accept(currentDay);
        }
    }
}
