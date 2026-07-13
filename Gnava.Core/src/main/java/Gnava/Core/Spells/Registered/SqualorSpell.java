package Gnava.Core.Spells.Registered;

import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public final class SqualorSpell extends AbstractSpell {
    @Override
    public SpellOutcome cast(SpellContext spellContext) {
        spellContext.settlementTarget().setWealthLevel(SettlementWealthLevel.DESTITUTE);

        return new SpellOutcome(
            isGood(),
            "You threw a squalormelon to %s".formatted(
                spellContext.settlementTarget().getName()
            )
        );
    }

    @Override
    public String getName() {
        return "Manifest squalor";
    }

    @Override
    public boolean isGood() {
        return false;
    }

    @Override
    public boolean needsExplicitTarget() {
        return true;
    }
}
