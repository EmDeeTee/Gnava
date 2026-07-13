package Gnava.Core.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.Statistics.Services.PlayerBodyCountTallyService;

public record SpellContext(
    GameState gameState,
    Settlement settlementTarget,
    CreatureNameGenerator creatureNameGenerator,
    PlayerBodyCountTallyService playerBodyCountTallyService
) { }
