package Gnava.Game.Commands;

import Gnava.Game.Managers.SettlementsManager;
import Gnava.Game.Models.Settlement;

public class CreateSettlementCommand implements Command<Settlement> {
    private final SettlementsManager settlementsManager;

    public CreateSettlementCommand(SettlementsManager settlementManager) {
        this.settlementsManager = settlementManager;
    }

    @Override
    public void execute(Settlement settlement) {
        if (!settlementsManager.tryCreateSettlement(settlement)) {
            System.err.println("CreateSettlementCommand didn't create a settlement, because game rules don't allow it");
        }
    }
}
