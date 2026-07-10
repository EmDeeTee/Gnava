package Gnava.Core.Statistics.Services;

import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.stereotype.Service;

@Service
public final class SettlementStatisticsManager {
    private final ISettlementRepository settlementRepository;

    public SettlementStatisticsManager(ISettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    public Integer getWorldPopulation() {
        return this.settlementRepository.getAll().stream().mapToInt(Settlement::getTotalPopulation).sum();
    }
}
