package Gnava.Core.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Repositories.ISettlementProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SpellManager extends AbstractGameManager {
    private final ISettlementProvider settlementProvider;
    private final List<AbstractSpell> spells;

    public SpellManager(
        GameState gameState,
        ISettlementProvider settlementProvider,
        List<AbstractSpell> spells
    ) {
        super(gameState);
        this.settlementProvider = settlementProvider;
        this.spells = spells;
    }

    public SpellOutcome castSpell(AbstractSpell spell) {
        return spell.cast(new SpellContext(gameState, settlementProvider.getRandom()));
    }
}
