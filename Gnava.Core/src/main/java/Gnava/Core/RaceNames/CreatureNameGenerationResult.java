package Gnava.Core.RaceNames;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;

import java.util.Optional;

public record CreatureNameGenerationResult(
    Optional<CreatureName> creatureName,
    SettlementPopulationType populationType
) { }
