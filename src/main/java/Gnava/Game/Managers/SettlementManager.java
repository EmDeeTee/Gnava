package Gnava.Game.Managers;

import Gnava.Game.Settlements.Settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SettlementManager {
    private final List<Settlement> settlements = new ArrayList<>();
    private final List<Consumer<List<Settlement>>> settlementListeners = new ArrayList<>();

    public SettlementManager() { }

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
