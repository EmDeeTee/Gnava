package Gnava.Core.Managers.Settlement;

import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public final class SettlementCreationPolicy {
    private static final int MAX_SETTLEMENTS = 10;
    private final ISettlementRepository settlementRepository;

    public SettlementCreationPolicy(ISettlementRepository settlementRepository) {
        this.settlementRepository = settlementRepository;
    }

    public SettlementCreationResult validate(CreateSettlementRequest target) {
        if (settlementRepository.count() >= MAX_SETTLEMENTS) {
            return new SettlementCreationResult(false, "Too many settlements");
        }
        if (target.name().startsWith("K")) {
            return new SettlementCreationResult(false, "Settlement names may not start with 'K'");
        }
        if (settlementRepository.getAll().stream().anyMatch(s -> Objects.equals(s.getName(), target.name()))) {
            return new SettlementCreationResult(false, "Settlements must have unique names");
        }

        return new SettlementCreationResult(true, "OK");
    }
}
