package Gnava.Core.Managers;

import Gnava.Core.EventDispatcher;
import Gnava.Core.GameState;
import Gnava.Core.Models.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;

import java.util.List;
import java.util.function.Consumer;

// TODO: Probably emit events, with type, like SettlementEvent.REMOVE, .CREATE etc.
public class SettlementManager extends AbstractGameManager {
    private final EventDispatcher<Settlement> settlementCreatedDispatcher = new EventDispatcher<>();
    private final ISettlementRepository settlementRepository;

    @SuppressWarnings("FieldCanBeLocal")
    private final int MAX_CONCURRENT_SETTLEMENTS = 10;

    public SettlementManager(GameState gameState, ISettlementRepository settlementRepository) {
        super(gameState);
        this.settlementRepository = settlementRepository;
    }

    public SettlementCreationResult tryCreateSettlement(Settlement settlement) {
        if (this.settlementRepository.count() >= MAX_CONCURRENT_SETTLEMENTS) {
            return new SettlementCreationResult(false, "Too many settlements");
        }

        createSettlement(settlement);
        return new SettlementCreationResult(true, "OK");
    }

    public int getSettlementCount() {
        return this.settlementRepository.count();
    }

    public List<Settlement> getSettlements() {
        return this.settlementRepository.getAll();
    }

    public Integer getWorldPopulation() {
        return this.settlementRepository.getAll().stream().mapToInt(Settlement::getTotalPopulation).sum();
    }

    public Settlement getRandomSettlement() {
        return this.settlementRepository.getRandom();
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        settlementCreatedDispatcher.addListener(listener);
    }

    private void createSettlement(Settlement settlement) {
        this.settlementRepository.save(settlement);
        settlementCreatedDispatcher.dispatch(settlement);
    }
}
