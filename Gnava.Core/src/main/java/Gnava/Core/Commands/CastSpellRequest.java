package Gnava.Core.Commands;

import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.Spells.AbstractSpell;
import org.jetbrains.annotations.Nullable;

public record CastSpellRequest(
    AbstractSpell spell,
    @Nullable Settlement target
) { }