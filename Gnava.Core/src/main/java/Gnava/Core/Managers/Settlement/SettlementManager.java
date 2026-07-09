package Gnava.Core.Managers.Settlement;

import Gnava.Core.EventDispatcher;
import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Models.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public final class SettlementManager extends AbstractGameManager {
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

    public List<Settlement> getSettlements() {
        return this.settlementRepository.getAll();
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        settlementCreatedDispatcher.addListener(listener);
    }

    private void createSettlement(Settlement settlement) {
        this.settlementRepository.save(settlement);
        settlementCreatedDispatcher.dispatch(settlement);
    }
}
