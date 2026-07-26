package Gnava.Desktop.Interface.Actions;

import Gnava.Core.CommandHandlers.ICommand;
import Gnava.Core.Managers.Settlement.SettlementCreationPolicy;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;

import java.awt.*;
import java.util.Optional;

public class CreateSettlementAction extends CommandAction<CreateSettlementRequest, SettlementCreationResult> {
    private final SettlementNameGenerator settlementNameGenerator;
    private final SettlementCreationPolicy settlementCreationPolicy;
    private final Window owner;

    public CreateSettlementAction(
        ICommand<CreateSettlementRequest, SettlementCreationResult> command,
        SettlementNameGenerator settlementNameGenerator,
        SettlementCreationPolicy settlementCreationPolicy,
        Window owner
    ) {
        super(command);
        this.settlementNameGenerator = settlementNameGenerator;
        this.settlementCreationPolicy = settlementCreationPolicy;
        this.owner = owner;
    }

    @Override
    protected Optional<CreateSettlementRequest> getInput() {
        return new CreateSettlementPopup(
            owner,
            settlementNameGenerator,
            settlementCreationPolicy
        ).show();
    }
}