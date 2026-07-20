package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;
import Gnava.Core.Commands.CommandAction;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;

import javax.swing.*;
import java.util.function.Supplier;

// uhhhhhh
public class CreateSettlementAction extends CommandAction<CreateSettlementRequest, SettlementCreationResult> {
    public CreateSettlementAction(
        Command<CreateSettlementRequest, SettlementCreationResult> command,
        Supplier<CreateSettlementRequest> supplier
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