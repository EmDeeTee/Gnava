package Gnava.GameApi.GameEvents.Settlements;

public record PopulationChange(
    int requestedAmount,
    int addedAmount,
    int overflow,
    int capacityRemaining
) { }
