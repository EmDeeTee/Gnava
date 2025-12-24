package Gnava.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameState {
    private static GameState instance = null;

    private final int currentDay = 0;
    private final List<Settlement> settlements = new ArrayList<>();
    private final List<Consumer<List<Settlement>>> settlementListeners = new ArrayList<>();

    private GameState() { }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
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

    private void notifySettlementListeners() {
        List<Settlement> snapshot = List.copyOf(this.settlements);
        for (Consumer<List<Settlement>> listener : settlementListeners) {
            listener.accept(snapshot);
        }
    }
}
