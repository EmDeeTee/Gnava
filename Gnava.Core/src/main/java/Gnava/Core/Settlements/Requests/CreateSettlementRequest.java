package Gnava.Core.Settlements.Requests;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;

public record CreateSettlementRequest(
    String name,
    int initialPopulation,
    int maxPopulation,
    SettlementPopulationType populationType,
    SettlementWealthLevel wealthLevel,
    boolean isPlayer
) { }
