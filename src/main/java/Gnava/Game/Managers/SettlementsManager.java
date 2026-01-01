package Gnava.Game.Managers;

import Gnava.Game.EventDispatcher;
import Gnava.Game.Settlements.Settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: Probably emit events, with type, like SettlementEvent.REMOVE, .CREATE etc.
public class SettlementsManager implements DispatchableManager<Settlement> {
    private final List<Settlement> settlements = new ArrayList<>();
    private final EventDispatcher<Settlement> dispatcher = new EventDispatcher<>();

    public SettlementsManager() { }

    public void createSettlement(Settlement settlement) {
        this.settlements.add(settlement);
        dispatcher.dispatch(settlement);
    }

    public Settlement[] getSettlements() {
        return List.copyOf(this.settlements).toArray(new Settlement[0]);
    }

    public Settlement getRandomSettlement() {
        Random random = new Random();
        return settlements.get(random.nextInt(settlements.size()));
    }

    public Settlement getPlayerSettlement() {
        return settlements.stream().filter(Settlement::isPlayer).findFirst().orElseThrow();
    }

    @Override
    public EventDispatcher<Settlement> getDispatcher() {
        return dispatcher;
    }
}
