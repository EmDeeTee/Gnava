package Gnava.Desktop.Interface.Frames.MainFrame.Components;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.Desktop.Interface.Actions.CreateSettlementAction;
import Gnava.Desktop.Interface.Actions.ShowWorldStatisticsAction;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    private final JMenu statisticsMenu = new JMenu("Statistics");
    private final JMenuItem showWorldStatisticsItem = new JMenuItem("World statistics");

    public MenuBar(
        MainFrame mainFrame,
        CreateSettlementCommand createSettlementCommand,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super();
        actionsMenu.add(createSettlementItem);
        statisticsMenu.add(showWorldStatisticsItem);

        CreateSettlementPopup popup =
                new CreateSettlementPopup(mainFrame);

        createSettlementItem.addActionListener(
            new CreateSettlementAction(
                createSettlementCommand,
                () -> new CreateSettlementPopup(mainFrame).show().orElseThrow()
            )
        );
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction(worldStatisticsProvider));

        add(actionsMenu);
        add(statisticsMenu);
    }
}
