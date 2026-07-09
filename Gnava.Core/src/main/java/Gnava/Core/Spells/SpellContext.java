package Gnava.Core.Spells;

import Gnava.Core.GameState;
import Gnava.Core.Models.Settlement;

public record SpellContext(GameState gameState, Settlement settlementTarget) { }
