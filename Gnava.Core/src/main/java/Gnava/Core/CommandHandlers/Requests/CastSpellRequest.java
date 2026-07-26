package Gnava.Core.CommandHandlers.Requests;

import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Spells.AbstractSpell;
import org.jetbrains.annotations.Nullable;

public record CastSpellRequest(
    AbstractSpell spell,
    @Nullable Settlement target
) { }