package Gnava.Interface.Menu.Actions;

import Gnava.Game.GameState;
import Gnava.Game.Settlements.Settlement;
import Gnava.Game.Settlements.SettlementPopulationType;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateSettlementAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Create settlement action");
        GameState.getInstance().tryCreateSettlement(new CreateSettlementPopup().show());
    }
}
