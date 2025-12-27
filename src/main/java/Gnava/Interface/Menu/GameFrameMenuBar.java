package Gnava.Interface.Menu;

import Gnava.Interface.Actions.CreateSettlementAction;

import javax.swing.*;

public class GameFrameMenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    public GameFrameMenuBar() {
        super();
        actionsMenu.add(createSettlementItem);

        createSettlementItem.addActionListener(new CreateSettlementAction());

        add(actionsMenu);
    }
}
