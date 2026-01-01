package Gnava.Game.Managers;

import Gnava.Game.Settlements.Settlement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

// TODO: Probably emit events, with type, like SettlementEvent.REMOVE, .CREATE etc.
public class SettlementsManager extends DispatchableManager<Settlement> {
    private final List<Settlement> settlements = new ArrayList<>();

    public SettlementsManager() { }

    public Stream<Settlement> stream() {
        return Arrays.stream(getSettlements());
    }

    // TODO: It can fail, but you don't know why
    public boolean tryCreateSettlement(Settlement settlement) {
        if (!canCreateSettlement(settlement)) {
            return false;
        }

        createSettlement(settlement);
        return true;
    }

    public boolean canCreateSettlement(Settlement settlement) {
        return true;
    }

    public Settlement[] getSettlements() {
        return List.copyOf(this.settlements).toArray(new Settlement[0]);
    }

    public Long getSettlementCount() {
        return stream().count();
    }

    public Integer getWorldPopulation() {
        return stream().mapToInt(Settlement::getTotalPopulation).sum();
    }

    public Settlement getRandomSettlement() {
        Random random = new Random();
        return settlements.get(random.nextInt(settlements.size()));
    }

    public Settlement getPlayerSettlement() {
        return settlements.stream().filter(Settlement::isPlayer).findFirst().orElseThrow();
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        getDispatcher().addListener(listener);
    }

    private void createSettlement(Settlement settlement) {
        this.settlements.add(settlement);
        dispatcher.dispatch(settlement);
    }
}
