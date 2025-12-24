package Gnava.Game.Settlements;

public record Settlement(
        String name,
        int totalPopulation,
        int maxPopulation,
        SettlementPopulationType populationType
) {
    @Override
    public String toString() {
        return name();
    }
}
