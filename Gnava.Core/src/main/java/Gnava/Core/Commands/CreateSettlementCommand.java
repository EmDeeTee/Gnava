package Gnava.Core.Commands;

import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Models.Settlement.Settlement;
import org.springframework.stereotype.Component;

@Component
public class CreateSettlementCommand implements Command<Settlement, SettlementCreationResult> {
    private final SettlementManager settlementManager;

    public CreateSettlementCommand(SettlementManager settlementManager) {
        this.settlementManager = settlementManager;
    }

    @Override
    public SettlementCreationResult execute(Settlement settlement) {
        return settlementManager.tryCreateSettlement(settlement);
    }
}
