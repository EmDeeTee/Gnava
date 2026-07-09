package Gnava.Core.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Models.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;

public record SpellContext(GameState gameState, Settlement settlementTarget, CreatureNameGenerator creatureNameGenerator) { }
