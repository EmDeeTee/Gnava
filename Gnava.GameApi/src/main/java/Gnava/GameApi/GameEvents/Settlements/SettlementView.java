package Gnava.GameApi.GameEvents.Settlements;

public record SettlementView(
    String name,
    int totalPopulation,
    int maxPopulation,
    SettlementPopulationType populationType,
    SettlementWealthLevel wealthLevel,
    boolean player
) {
    public int populationCapacityRemaining() {
        return maxPopulation - totalPopulation;
    }
}
