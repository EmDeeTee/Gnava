package Gnava.Core.Managers.Spells;

import Gnava.Core.TimeState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Spells.AbstractSpell;
import Gnava.Core.Spells.SpellContext;
import Gnava.Core.Spells.SpellOutcome;
import Gnava.Core.Statistics.Services.PlayerBodyCountTallyService;
import Gnava.Core.Statistics.Services.SpellStatisticsManager;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public final class SpellManager extends AbstractGameManager {
    private final ISettlementProvider settlementProvider;
    private final SpellStatisticsManager spellStatisticsManager;
    private final CreatureNameGenerator creatureNameGenerator;
    private final PlayerBodyCountTallyService playerBodyCountTallyService;

    public SpellManager(
        TimeState timeState,
        ISettlementProvider settlementProvider,
        SpellStatisticsManager spellStatisticsManager,
        CreatureNameGenerator creatureNameGenerator,
        PlayerBodyCountTallyService playerBodyCountTallyService
    ) {
        super(timeState);
        this.settlementProvider = settlementProvider;
        this.spellStatisticsManager = spellStatisticsManager;
        this.creatureNameGenerator = creatureNameGenerator;
        this.playerBodyCountTallyService = playerBodyCountTallyService;
    }

    public SpellOutcome castSpell(AbstractSpell spell, @Nullable Settlement target) {
        target = target == null ? settlementProvider.getRandom() : target;

        SpellOutcome spellOutcome = spell.cast(
            new SpellContext(
                    timeState,
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
