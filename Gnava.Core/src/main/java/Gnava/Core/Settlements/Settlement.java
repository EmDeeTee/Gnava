package Gnava.Core.Settlements;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;

public final class Settlement {
    private final String name;
    private int totalPopulation;
    private int maxPopulation;
    private final SettlementPopulationType populationType;
    private SettlementWealthLevel wealthLevel;
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

    public AddPopulationResult addPopulation(int requestedAmount) {
        int addedAmount = Math.min(requestedAmount + totalPopulation, maxPopulation) - totalPopulation;
        boolean allIn = addedAmount == requestedAmount;
        int overflow = requestedAmount - addedAmount;

        totalPopulation += addedAmount;

        return new AddPopulationResult(
            requestedAmount,
            addedAmount,
            allIn,
            overflow,
            getPopulationCapacityRemaining()
        );
    }

    public int getPopulationCapacityRemaining() {
        return maxPopulation - totalPopulation;
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

    public void setWealthLevel(SettlementWealthLevel wealthLevel) {
        this.wealthLevel = wealthLevel;
    }
}
