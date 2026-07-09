package Gnava.Desktop.Interface.Frames.MainFrame.Components;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.GameState;
import Gnava.Core.Managers.Settlement.SettlementManager;
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
        GameState gameState,
        MainFrame mainFrame,
        SettlementManager settlementManager,
        WorldStatisticsProvider worldStatisticsProvider
    ) {
        super();
        actionsMenu.add(createSettlementItem);
        statisticsMenu.add(showWorldStatisticsItem);

        createSettlementItem.addActionListener(
            new CreateSettlementAction(
                new CreateSettlementCommand(settlementManager),
                () -> new CreateSettlementPopup(mainFrame).show()
            )
        );
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction(worldStatisticsProvider));

        add(actionsMenu);
        add(statisticsMenu);
    }
}
