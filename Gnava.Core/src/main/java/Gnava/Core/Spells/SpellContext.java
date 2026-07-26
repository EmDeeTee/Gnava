package Gnava.Core.Spells;

import Gnava.Core.TimeState;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.Statistics.Services.PlayerBodyCountTallyService;

public record SpellContext(
    TimeState timeState,
    Settlement settlementTarget,
    CreatureNameGenerator creatureNameGenerator,
    PlayerBodyCountTallyService playerBodyCountTallyService
) { }
