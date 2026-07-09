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
    private final SettlementCreationPolicy settlementCreationPolicy;

    public SettlementManager(
        GameState gameState,
        ISettlementRepository settlementRepository,
        SettlementCreationPolicy settlementCreationPolicy
    ) {
        super(gameState);
        this.settlementRepository = settlementRepository;
        this.settlementCreationPolicy = settlementCreationPolicy;
    }

    public SettlementCreationResult tryCreateSettlement(Settlement settlement) {
        SettlementCreationResult result = settlementCreationPolicy.validate(settlement);
        if (!result.ok()) {
            return result;
        }

        createSettlement(settlement);
        return result;
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
