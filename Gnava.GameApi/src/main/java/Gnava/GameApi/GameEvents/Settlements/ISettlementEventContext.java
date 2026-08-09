package Gnava.GameApi.GameEvents.Settlements;

import Gnava.GameApi.GameEvents.IGameEventContext;

public interface ISettlementEventContext extends IGameEventContext {
    SettlementView settlement();

    PopulationChange addPopulation(int amount);

    void expandPopulationCapacity(int amount);

    void setWealthLevel(SettlementWealthLevel wealthLevel);
}
