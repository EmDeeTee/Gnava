package Gnava.Core.Settlements.Requests;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;

public record CreateSettlementRequest(
    String name,
    int initialPopulation,
    int maxPopulation,
    SettlementPopulationType populationType,
    SettlementWealthLevel wealthLevel,
    boolean isPlayer
) { }
