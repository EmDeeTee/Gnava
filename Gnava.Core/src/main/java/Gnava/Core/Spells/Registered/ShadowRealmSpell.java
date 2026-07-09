package Gnava.Core.Spells.Registered;

import Gnava.Core.Models.Settlement;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

@Component
public final class ShadowRealmSpell extends AbstractSpell {
    @Override
    public SpellOutcome cast(SpellContext spellContext) {
        Settlement target = spellContext.settlementTarget();

        if (target.getTotalPopulation() == 0) {
            return new SpellOutcome(true, "Nobody to select in the settlement of %s".formatted(target.getName()));
        }

        target.setTotalPopulation(target.getTotalPopulation() - 1);
        // TODO: Add a special class with race specific names, so I can pull a random name for events like this
        // ex. If the settlement has the GNOME race, the names will be gnomish sounding
        return new SpellOutcome(isGood(), "You banished %s from %s to the shadow realm.".formatted("NAME", target.getName()));
    }

    @Override
    public String getName() {
        return "Banish to shadow realm";
    }

    @Override
    public boolean isGood() {
        return false;
    }
}
