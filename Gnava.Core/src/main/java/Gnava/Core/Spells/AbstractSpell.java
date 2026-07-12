package Gnava.Core.Spells;

public abstract class AbstractSpell {
    public abstract SpellOutcome cast(SpellContext spellContext);
    public abstract String getName();
    public abstract boolean isGood();
    public abstract boolean needsExplicitTarget();
}
