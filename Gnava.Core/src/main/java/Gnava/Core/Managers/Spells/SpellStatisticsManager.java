package Gnava.Core.Managers.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import org.springframework.stereotype.Service;

@Service
public final class SpellStatisticsManager extends AbstractGameManager {
    private int castedSpells = 0;
    private int badOutcomes = 0;
    private int goodOutcomes = 0;

    public SpellStatisticsManager(GameState gameState) {
        super(gameState);
    }

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
