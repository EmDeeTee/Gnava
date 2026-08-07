package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.Settlements.AddPopulationResult;
import Gnava.Core.TimeState;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.PopulationChange;
import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;

public class SettlementEventContext extends WorldEventContext implements ISettlementEventContext {
    private final Settlement targetSettlement;

    public SettlementEventContext(
        TimeState timeState,
        WorldStatisticsProvider worldStatisticsProvider,
        Settlement targetSettlement
    ) {
        super(timeState, worldStatisticsProvider);
        this.targetSettlement = targetSettlement;
    }

    @Override
    public SettlementView settlement() {
        return new SettlementView(
            targetSettlement.getName(),
            targetSettlement.getTotalPopulation(),
            targetSettlement.getMaxPopulation(),
            targetSettlement.getPopulationType(),
            targetSettlement.getWealthLevel(),
            targetSettlement.isPlayer()
        );
    }

    @Override
    public PopulationChange addPopulation(int amount) {
        AddPopulationResult result = targetSettlement.addPopulation(amount);

        return new PopulationChange(
            result.requestedAmount(),
            result.addedAmount(),
            result.overflow(),
            result.capacityRemaining()
        );
    }

    @Override
    public void expandPopulationCapacity(int amount) {
        targetSettlement.setMaxPopulation(targetSettlement.getMaxPopulation() + amount);
    }

    @Override
    public void setWealthLevel(SettlementWealthLevel wealthLevel) {
        targetSettlement.setWealthLevel(wealthLevel);
    }
}
