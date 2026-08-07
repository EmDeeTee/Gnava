package Gnava.Core.Spells.Registered;

import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public final class DutchMoneySpell extends AbstractSpell {
    @Override
    public SpellOutcome cast(SpellContext spellContext) {
        spellContext.settlementTarget().setWealthLevel(SettlementWealthLevel.AFFLUENT);

        return new SpellOutcome(
            isGood(),
            "You bestowed richness upon %s".formatted(
                spellContext.settlementTarget().getName()
            )
        );
    }

    @Override
    public String getName() {
        return "Dutch money";
    }

    @Override
    public boolean isGood() {
        return true;
    }

    @Override
    public boolean needsExplicitTarget() {
        return true;
    }
}
