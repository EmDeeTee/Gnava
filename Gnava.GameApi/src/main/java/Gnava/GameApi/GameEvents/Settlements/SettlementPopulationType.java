package Gnava.GameApi.GameEvents.Settlements;

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
