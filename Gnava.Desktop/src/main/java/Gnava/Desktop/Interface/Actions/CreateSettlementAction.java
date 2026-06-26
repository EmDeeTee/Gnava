package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;
import Gnava.Core.Commands.CommandAction;
import Gnava.Core.Models.Settlement;

import java.util.Optional;
import java.util.function.Supplier;

public class CreateSettlementAction extends CommandAction<Settlement> {
    public CreateSettlementAction(Command<Settlement> command, Supplier<Optional<Settlement>> inputSupplier) {
        super(command, inputSupplier);
    }
}
