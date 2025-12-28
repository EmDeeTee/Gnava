package Gnava.Game.Commands;

import Gnava.Game.GameState;
import Gnava.Game.Settlements.Settlement;

public class CreateSettlementCommand implements Command<Settlement> {
    @Override
    public void execute(Settlement settlement) {
        if (!GameState.getInstance().tryCreateSettlement(settlement)) {
            System.err.println("CreateSettlementCommand didn't create a settlement, because game rules don't allow it");
        }
    }
}
