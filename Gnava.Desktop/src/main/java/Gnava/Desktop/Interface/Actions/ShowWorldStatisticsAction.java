package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.GameState;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;

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
