package Gnava.Core.Settlements.Enums;

public enum SettlementPopulationType {
    GNOME,
    DWARF,
    GOBLIN;

    public String plural() {
        return switch (this) {
            case GNOME -> "gnomes";
            case DWARF -> "dwarves";
            case GOBLIN -> "goblins";
        };
    }
}
