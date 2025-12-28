package Gnava.Interface.Actions;

import Gnava.Game.Commands.CommandAction;
import Gnava.Game.Commands.CreateSettlementCommand;
import Gnava.Game.Settlements.Settlement;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

public class CreateSettlementAction extends CommandAction<Settlement> {
    public CreateSettlementAction() {
        super(new CreateSettlementCommand(), () -> new CreateSettlementPopup().show());
    }
}
