package Gnava.Interface.Menu;

import Gnava.Interface.Actions.CreateSettlementAction;
import Gnava.Interface.Actions.ShowWorldStatisticsAction;

import javax.swing.*;

public class GameFrameMenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    private final JMenu statisticsMenu = new JMenu("Statistics");
    private final JMenuItem showWorldStatisticsItem = new JMenuItem("World statistics");

    public GameFrameMenuBar() {
        super();
        actionsMenu.add(createSettlementItem);
        statisticsMenu.add(showWorldStatisticsItem);

        createSettlementItem.addActionListener(new CreateSettlementAction());
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction());

        add(actionsMenu);
        add(statisticsMenu);
    }
}
