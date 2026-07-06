package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowWorldStatisticsAction extends AbstractAction {
    private final WorldStatisticsProvider worldStatisticsProvider;

    public ShowWorldStatisticsAction(WorldStatisticsProvider worldStatisticsProvider) {
        this.worldStatisticsProvider = worldStatisticsProvider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WorldStatistics stats = worldStatisticsProvider.getWorldStatistics();

        String html = """
        Population: %d<br>
        Settlements: %d<br>
        """.formatted(
                stats.population(),
                stats.settlementCount()
        );

        MainFrame owner = (MainFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());

        new PlaintextPopup(owner, html).show();
    }
}
