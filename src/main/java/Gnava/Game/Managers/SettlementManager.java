package Gnava.Game.Managers;

import Gnava.Game.Settlements.Settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SettlementManager {
    private final List<Settlement> settlements = new ArrayList<>();
    private final List<Consumer<Settlement>> settlementListeners = new ArrayList<>();

    public SettlementManager() { }

    public void createSettlement(Settlement settlement) {
        this.settlements.add(settlement);
        notifySettlementListeners(settlement);
    }

    public Settlement[] getSettlements() {
        return List.copyOf(this.settlements).toArray(new Settlement[0]);
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        settlementListeners.add(listener);
    }

    // TODO: Probably emit events, with type, like SettlementEvent.REMOVE, .CREATE etc.
    private void notifySettlementListeners(Settlement newSettlement) {
        for (Consumer<Settlement> listener : settlementListeners) {
            listener.accept(newSettlement);
        }
    }
}
