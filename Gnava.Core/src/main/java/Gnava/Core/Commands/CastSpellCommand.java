package Gnava.Core.Commands;

import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellManager;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public class CastSpellCommand implements Command<AbstractSpell, SpellOutcome> {
    private final SpellManager spellManager;

    public CastSpellCommand(SpellManager spellManager) {
        this.spellManager = spellManager;
    }

    @Override
    public SpellOutcome execute(AbstractSpell spell) {
        return spellManager.castSpell(spell);
    }
}
