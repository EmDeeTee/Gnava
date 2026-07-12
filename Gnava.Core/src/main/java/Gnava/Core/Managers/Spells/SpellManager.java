package Gnava.Core.Managers.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import Gnava.Core.Statistics.Services.PlayerBodyCountTallyService;
import Gnava.Core.Statistics.Services.SpellStatisticsManager;
import org.springframework.stereotype.Service;

@Service
public final class SpellManager extends AbstractGameManager {
    private final ISettlementProvider settlementProvider;
    private final SpellStatisticsManager spellStatisticsManager;
    private final CreatureNameGenerator creatureNameGenerator;
    private final PlayerBodyCountTallyService playerBodyCountTallyService;

    public SpellManager(
        GameState gameState,
        ISettlementProvider settlementProvider,
        SpellStatisticsManager spellStatisticsManager,
        CreatureNameGenerator creatureNameGenerator,
        PlayerBodyCountTallyService playerBodyCountTallyService
    ) {
        super(gameState);
        this.settlementProvider = settlementProvider;
        this.spellStatisticsManager = spellStatisticsManager;
        this.creatureNameGenerator = creatureNameGenerator;
        this.playerBodyCountTallyService = playerBodyCountTallyService;
    }

    public SpellOutcome castSpell(AbstractSpell spell) {
        return castSpell(spell, settlementProvider.getRandom());
    }

    public SpellOutcome castSpell(AbstractSpell spell, Settlement target) {
        SpellOutcome spellOutcome = spell.cast(
            new SpellContext(
                gameState,
                target,
                creatureNameGenerator,
                playerBodyCountTallyService
            )
        );

        updateSpellStatistics(spellOutcome);

        return spellOutcome;
    }

    private void updateSpellStatistics(SpellOutcome spellOutcome) {
        if (spellOutcome.isGood()) {
            spellStatisticsManager.incrementGoodOutcomes();
        } else {
            spellStatisticsManager.incrementBadOutcomes();
        }
        spellStatisticsManager.incrementCastedSpells();
    }
}
