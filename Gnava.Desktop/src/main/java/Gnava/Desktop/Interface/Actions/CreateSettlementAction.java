package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;
import Gnava.Core.Commands.CommandAction;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Models.Settlement;

import javax.swing.*;
import java.util.function.Supplier;

public class CreateSettlementAction extends CommandAction<Settlement, SettlementCreationResult> {
    public CreateSettlementAction(
        Command<Settlement, SettlementCreationResult> command,
        Supplier<Settlement> supplier
    ) {
        super(command, supplier);
    }

    @Override
    protected void handleResult(SettlementCreationResult result) {
        if (!result.ok()) {
            JOptionPane.showMessageDialog(
                null,
                result.reason()
            );
        }
    }
}