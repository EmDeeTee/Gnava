package Gnava.Core.Commands;

import Gnava.Core.Managers.Spells.SpellManager;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public class CastSpellCommand implements Command<CastSpellRequest, SpellOutcome> {
    private final SpellManager spellManager;

    public CastSpellCommand(SpellManager spellManager) {
        this.spellManager = spellManager;
    }

    @Override
    public SpellOutcome execute(CastSpellRequest request) {
        if (request.target() != null) {
            return spellManager.castSpell(request.spell(), request.target());
        } else {
            return spellManager.castSpell(request.spell());
        }
    }
}
