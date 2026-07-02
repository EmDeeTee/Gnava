package Gnava.Repositories;

import Gnava.Core.Models.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SettlementRepository implements ISettlementRepository {
    private final List<Settlement> settlements = new ArrayList<>();

    @Override
    public void save(Settlement settlement) {
        this.settlements.add(settlement);
    }

    @Override
    public List<Settlement> getAll() {
        return Collections.unmodifiableList(settlements);
    }

    @Override
    public int count() {
        return settlements.size();
    }

    @Override
    public Settlement getRandom() {
        int count = count();

        if (count == 0) {
            throw new IndexOutOfBoundsException();
        }

        return settlements.get(ThreadLocalRandom.current().nextInt(count));
    }

    @Override
    public Settlement getPlayerSettlement() {
        // A player settlement should always exist, so this should be safe
        return settlements.stream().filter(Settlement::isPlayer).findFirst().orElseThrow();
    }
}
