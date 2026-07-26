package Gnava.Core.CommandHandlers;

import Gnava.Core.CommandHandlers.Requests.CastSpellRequest;
import Gnava.Core.Managers.Spells.SpellManager;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public class CastSpellHandler implements ICommand<CastSpellRequest, SpellOutcome> {
    private final SpellManager spellManager;

    public CastSpellHandler(SpellManager spellManager) {
        this.spellManager = spellManager;
    }

    @Override
    public SpellOutcome execute(CastSpellRequest request) {
        return spellManager.castSpell(request.spell(), request.target());
    }
}
