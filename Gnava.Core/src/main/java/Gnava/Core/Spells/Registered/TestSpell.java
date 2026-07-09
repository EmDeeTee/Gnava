package Gnava.Core.Spells.Registered;

import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public final class TestSpell extends AbstractSpell {
    @Override
    public SpellOutcome cast(SpellContext spellContext) {
        return new SpellOutcome(isGood(), "Hello test spell was cast on %s".formatted(spellContext.settlementTarget().getName()));
    }

    @Override
    public String getName() {
        return "Test spell";
    }

    @Override
    public boolean isGood() {
        return true;
    }
}
