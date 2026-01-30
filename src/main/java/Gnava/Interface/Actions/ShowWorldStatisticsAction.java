package Gnava.Interface.Actions;

import Gnava.Game.DataTransferObjects.WorldStatistics;
import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.PlaintextPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowWorldStatisticsAction extends AbstractAction {
    private final GameState gameState;

    public ShowWorldStatisticsAction(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorldStatistics stats = gameState.getWorldStatistics();

        String html = """
        Population: %d<br>
        Settlements: %d<br>
        """.formatted(
                stats.population(),
                stats.settlementCount()
        );

        GameFrame owner = (GameFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());

        new PlaintextPopup(owner, html).show();
    }
}
