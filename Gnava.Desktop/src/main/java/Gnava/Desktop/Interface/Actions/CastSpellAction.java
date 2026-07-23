package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.CastSpellRequest;
import Gnava.Core.Commands.Command;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellOutcome;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import org.jspecify.annotations.Nullable;

import java.awt.*;
import java.util.Optional;

public class CastSpellAction extends CommandAction<CastSpellRequest, SpellOutcome> {
    private final Window owner;
    private final AbstractSpell spell;
    private final @Nullable Settlement target;

    public CastSpellAction(
        Command<CastSpellRequest, SpellOutcome> command,
        Window owner,
        AbstractSpell spell,
        @Nullable Settlement target
    ) {
        super(command);
        this.owner = owner;
        this.spell = spell;
        this.target = target;
    }

    @Override
    protected Optional<CastSpellRequest> getInput() {
        return Optional.of(new CastSpellRequest(spell, target));
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
