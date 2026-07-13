package Gnava.Core.Settlements;

public record AddPopulationResult(
    int requestedAmount,
    int addedAmount,
    boolean hasAddedEveryone,
    int overflow,
    int capacityRemaining
) { }
