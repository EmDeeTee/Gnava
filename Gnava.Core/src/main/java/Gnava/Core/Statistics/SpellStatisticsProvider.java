package Gnava.Core.Statistics;

import Gnava.Core.Dto.SpellStatistics;
import Gnava.Core.Managers.Spells.SpellStatisticsManager;
import org.springframework.stereotype.Service;

@Service
public final class SpellStatisticsProvider {
    private final SpellStatisticsManager spellStatisticsManager;

    public SpellStatisticsProvider(SpellStatisticsManager spellStatisticsManager) {
        this.spellStatisticsManager = spellStatisticsManager;
    }

    public SpellStatistics getSpellStatistics() {
        return new SpellStatistics(
            spellStatisticsManager.getCastedSpells(),
            spellStatisticsManager.getGoodOutcomes(),
            spellStatisticsManager.getBadOutcomes()
        );
    }
}
