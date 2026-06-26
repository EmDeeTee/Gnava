package Gnava.Desktop.Interface.Frame;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.GameState;
import Gnava.Desktop.Interface.Actions.CreateSettlementAction;
import Gnava.Desktop.Interface.Actions.ShowWorldStatisticsAction;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;

import javax.swing.*;

public class GameFrameMenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    private final JMenu statisticsMenu = new JMenu("Statistics");
    private final JMenuItem showWorldStatisticsItem = new JMenuItem("World statistics");

    public GameFrameMenuBar(GameState gameState, GameFrame gameFrame) {
        super();
        actionsMenu.add(createSettlementItem);
        statisticsMenu.add(showWorldStatisticsItem);

        createSettlementItem.addActionListener(
            new CreateSettlementAction(
                new CreateSettlementCommand(gameState.getSettlementManager()),
                () -> new CreateSettlementPopup(gameFrame).show()
            )
        );
        showWorldStatisticsItem.addActionListener(new ShowWorldStatisticsAction(gameState));

        add(actionsMenu);
        add(statisticsMenu);
    }
}
