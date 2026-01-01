package Gnava.Interface.Actions;

import Gnava.Game.DataTransferObjects.WorldStatistics;
import Gnava.Game.GameState;
import Gnava.Interface.Popups.Presets.PlaintextPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowWorldStatisticsAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();
        WorldStatistics worldStatistics = GameState.getInstance().getWorldStatistics();

        sb.append("Population: ").append(worldStatistics.population()).append("<br>");
        sb.append("Settlements: ").append(worldStatistics.settlementCount()).append("<br>");

        new PlaintextPopup(sb.toString()).show();
    }
}
