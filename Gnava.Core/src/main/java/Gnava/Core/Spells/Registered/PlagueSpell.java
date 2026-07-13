package Gnava.Core.Spells.Registered;

import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public final class PlagueSpell extends AbstractSpell {
    @Override
    public SpellOutcome cast(SpellContext spellContext) {
        int damage = ThreadLocalRandom.current().nextInt(
            spellContext.settlementTarget().getTotalPopulation() / 4,
            spellContext.settlementTarget().getTotalPopulation() / 2
        );

        spellContext.settlementTarget().addPopulation(-damage);
        spellContext.playerBodyCountTallyService().incrementPlayerBodyCount(damage);

        return new SpellOutcome(
            isGood(),
            "You manifested a horrible plague on %s. %d %s ended up perishing.".formatted(
                spellContext.settlementTarget().getName(),
                damage,
                spellContext.settlementTarget().getPopulationType().plural()
            )
        );
    }

    @Override
    public String getName() {
        return "Plague";
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
