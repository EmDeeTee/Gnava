package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Statistics.Records.SpellStatistics;
import Gnava.Core.Statistics.SpellStatisticsProvider;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShowSpellsStatisticsAction extends AbstractAction {
    private final SpellStatisticsProvider spellStatisticsProvider;

    public ShowSpellsStatisticsAction(SpellStatisticsProvider spellStatisticsProvider) {
        this.spellStatisticsProvider = spellStatisticsProvider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SpellStatistics stats = spellStatisticsProvider.getSpellStatistics();

        String html = """
        Total spells casted: %d<br>
            - Good outcomes: %d<br>
            - Bad outcomes: %d<br><br>
            Ratio Good:Bad: %.2f
        """.formatted(
            stats.spellsCasted(),
            stats.goodOutcomes(),
            stats.badOutcomes(),
            stats.badOutcomes() == 0 ? 0.0 : (float) stats.goodOutcomes() / stats.badOutcomes()
        );

        MainFrame owner = (MainFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());

        new PlaintextPopup(owner, html).show();
    }
}
