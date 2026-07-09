package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;
import Gnava.Core.Commands.CommandAction;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellOutcome;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;

import java.awt.*;
import java.util.function.Supplier;

public class CastSpellAction extends CommandAction<AbstractSpell, SpellOutcome> {
    private final Window owner;

    public CastSpellAction(Command<AbstractSpell, SpellOutcome> command, Supplier<AbstractSpell> inputSupplier, Window owner) {
        super(command, inputSupplier);
        this.owner = owner;
    }

    @Override
    protected void handleResult(SpellOutcome result) {
        new PlaintextPopup(
            owner,
            result.description() + "<br /><br /> This outcome is considered %s".formatted(result.isGood() ? "good" : "bad"),
            "You casted a spell!"
        ).show();
    }
}
