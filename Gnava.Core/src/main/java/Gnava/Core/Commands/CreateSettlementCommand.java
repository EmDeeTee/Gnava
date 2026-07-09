package Gnava.Core.Commands;

import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Models.Settlement;

public class CreateSettlementCommand implements Command<Settlement> {
    private final SettlementManager settlementManager;

    public CreateSettlementCommand(SettlementManager settlementManager) {
        this.settlementManager = settlementManager;
    }

    @Override
    public void execute(Settlement settlement) {
        SettlementCreationResult result = settlementManager.tryCreateSettlement(settlement);
        if (!result.ok()) {
            System.err.println("CreateSettlementCommand didn't create a settlement, because game rules don't allow it: " + result.reason());
        }
    }
}
