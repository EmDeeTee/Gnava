package Gnava.Core.RaceNames;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;

import java.util.Optional;

public record CreatureNameGenerationResult(
    Optional<CreatureName> creatureName,
    SettlementPopulationType populationType
) { }
