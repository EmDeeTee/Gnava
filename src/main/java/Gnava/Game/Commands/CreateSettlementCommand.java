package Gnava.Game.Commands;

import Gnava.Game.Managers.SettlementManager;
import Gnava.Game.Models.Settlement;

public class CreateSettlementCommand implements Command<Settlement> {
    private final SettlementManager settlementManager;

    public CreateSettlementCommand(SettlementManager settlementManager) {
        this.settlementManager = settlementManager;
    }

    @Override
    public void execute(Settlement settlement) {
        if (!settlementManager.tryCreateSettlement(settlement)) {
            System.err.println("CreateSettlementCommand didn't create a settlement, because game rules don't allow it");
        }
    }
}
