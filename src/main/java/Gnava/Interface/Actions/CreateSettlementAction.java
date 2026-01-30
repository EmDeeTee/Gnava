package Gnava.Interface.Actions;

import Gnava.Game.Commands.Command;
import Gnava.Game.Commands.CommandAction;
import Gnava.Game.Settlements.Settlement;

import java.util.Optional;
import java.util.function.Supplier;

public class CreateSettlementAction extends CommandAction<Settlement> {
    public CreateSettlementAction(Command<Settlement> command, Supplier<Optional<Settlement>> inputSupplier) {
        super(command, inputSupplier);
    }
}
