package Gnava.Core.Models;

import Gnava.Core.Models.Enums.SettlementPopulationType;
import Gnava.Core.Models.Enums.SettlementWealthLevel;

public class Settlement {
    private final String name;
    private int totalPopulation;
    private int maxPopulation;
    private final SettlementPopulationType populationType;
    private final SettlementWealthLevel wealthLevel;
    private final boolean isPlayer;

    public Settlement(
        String name,
        int totalPopulation,
        int maxPopulation,
        SettlementPopulationType populationType,
        SettlementWealthLevel wealthLevel,
        boolean isPlayer
    ) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.maxPopulation = maxPopulation;
        this.populationType = populationType;
        this.wealthLevel = wealthLevel;
        this.isPlayer = isPlayer;
    }

    @Override
    public String toString() {
        return name;
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

    public boolean isPlayer() {
        return isPlayer;
    }

    public SettlementWealthLevel getWealthLevel() {
        return wealthLevel;
    }
}
