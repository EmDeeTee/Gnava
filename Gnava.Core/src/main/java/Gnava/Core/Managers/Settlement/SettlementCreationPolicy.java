package Gnava.Core.Managers.Settlement;

import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.stereotype.Component;

@Component
public final class SettlementCreationPolicy {
    private static final int MAX_SETTLEMENTS = 10;
    private final ISettlementRepository settlementRepository;

    public SettlementCreationPolicy(ISettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    public SettlementCreationResult validate(Settlement target) {
        if (settlementRepository.count() >= MAX_SETTLEMENTS) {
            return new SettlementCreationResult(false, "Too many settlements");
        }
        if (target.getName().startsWith("K")) {
            return new SettlementCreationResult(false, "Settlement names may not start with 'K'");
        }

        return new SettlementCreationResult(true, "OK");
    }
}
