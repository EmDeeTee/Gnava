package Gnava.Core.Settlements.NameGenerator;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;

public record SettlementNameGenerationResult(
    String name,
    SettlementPopulationType populationType
) { }
