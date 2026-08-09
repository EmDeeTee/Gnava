package Gnava.Core.Settlements.NameGenerator;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;

public record SettlementNameGenerationResult(
    String name,
    SettlementPopulationType populationType
) { }
