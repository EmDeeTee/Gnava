package Gnava.Core.Managers.Settlement;

import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Models.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.stereotype.Service;

@Service
public final class SettlementStatisticsManager extends AbstractGameManager {
    private final ISettlementRepository settlementRepository;

    public SettlementStatisticsManager(GameState gameState, ISettlementRepository settlementRepository) {
        super(gameState);
        this.settlementRepository = settlementRepository;
    }

    public Integer getWorldPopulation() {
        return this.settlementRepository.getAll().stream().mapToInt(Settlement::getTotalPopulation).sum();
    }
}
