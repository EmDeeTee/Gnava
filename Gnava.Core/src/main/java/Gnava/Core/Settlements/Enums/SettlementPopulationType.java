package Gnava.Core.Settlements.Enums;

public enum SettlementPopulationType {
    GNOME,
    DWARF,
    GOBLIN;

    // TODO: That probably doesn't belong in Core
    public String plural() {
        return switch (this) {
            case GNOME -> "gnomes";
            case DWARF -> "dwarves";
            case GOBLIN -> "goblins";
        };
    }
}
