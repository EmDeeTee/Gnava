package Gnava.Core.Spells.Registered;

import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.DefaultCreatureName;
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

        target.addPopulation(-1);
        spellContext.playerBodyCountTallyService().incrementPlayerBodyCount();
        return new SpellOutcome(
            isGood(),
            "You banished a %s, %s from %s to the shadow realm.".formatted(
                spellContext.settlementTarget().getPopulationType().name().toLowerCase(),
                spellContext
                    .creatureNameGenerator()
                    .generate(spellContext.settlementTarget().getPopulationType())
                    .creatureName()
                    .orElse(DefaultCreatureName.get())
                    .fullName(),
                target.getName()
            )
        );
    }

    @Override
    public String getName() {
        return "Banish to shadow realm";
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
