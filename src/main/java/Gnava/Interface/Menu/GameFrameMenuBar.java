package Gnava.Interface.Menu;

import Gnava.Game.Commands.CommandAction;
import Gnava.Game.Commands.CreateSettlementCommand;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

import javax.swing.*;

public class GameFrameMenuBar extends JMenuBar {
    private final JMenu actionsMenu = new JMenu("Actions");
    private final JMenuItem createSettlementItem = new JMenuItem("Create settlement");

    public GameFrameMenuBar() {
        super();
        actionsMenu.add(createSettlementItem);

        createSettlementItem.addActionListener(new CommandAction<>(
            new CreateSettlementCommand(),
            () -> new CreateSettlementPopup().show()
        ));

        add(actionsMenu);
    }
}
