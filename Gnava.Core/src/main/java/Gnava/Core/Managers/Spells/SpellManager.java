package Gnava.Core.Managers.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import org.springframework.stereotype.Service;

@Service
public final class SpellManager extends AbstractGameManager {
    private final ISettlementProvider settlementProvider;
    private final SpellStatisticsManager spellStatisticsManager;

    public SpellManager(
        GameState gameState,
        ISettlementProvider settlementProvider,
        SpellStatisticsManager spellStatisticsManager
    ) {
        super(gameState);
        this.settlementProvider = settlementProvider;
        this.spellStatisticsManager = spellStatisticsManager;
    }

    public SpellOutcome castSpell(AbstractSpell spell) {
        SpellOutcome spellOutcome = spell.cast(new SpellContext(gameState, settlementProvider.getRandom()));

        if (spellOutcome.isGood()) {
            spellStatisticsManager.incrementGoodOutcomes();
        } else {
            spellStatisticsManager.incrementBadOutcomes();
        }
        spellStatisticsManager.incrementCastedSpells();

        return spellOutcome;
    }
}
