package Gnava.Game.Settlements;

public class Settlement {
    private final String name;
    private int totalPopulation;
    private int maxPopulation;
    private final SettlementPopulationType populationType;

    public Settlement(
        String name,
        int totalPopulation,
        int maxPopulation,
        SettlementPopulationType populationType
    ) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.maxPopulation = maxPopulation;
        this.populationType = populationType;
    }

    public String getName() {
        return name;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int newMax) {
        this.maxPopulation = newMax;
    }

    public void setTotalPopulation(int newPop) {
        totalPopulation = newPop;
    }

    public SettlementPopulationType getPopulationType() {
        return populationType;
    }

    @Override
    public String toString() {
        return name;
    }
}
