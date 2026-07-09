package Gnava.Core.Statistics.Services;

import org.springframework.stereotype.Service;

@Service
public final class SpellStatisticsManager {
    private int castedSpells = 0;
    private int badOutcomes = 0;
    private int goodOutcomes = 0;

    public void incrementCastedSpells() {
        castedSpells++;
    }

    public int getCastedSpells() {
        return castedSpells;
    }

    public int getBadOutcomes() {
        return badOutcomes;
    }

    public void incrementBadOutcomes() {
        badOutcomes++;
    }

    public int getGoodOutcomes() {
        return goodOutcomes;
    }

    public void incrementGoodOutcomes() {
        goodOutcomes++;
    }
}
