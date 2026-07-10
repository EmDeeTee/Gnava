package Gnava.Core.Models.Settlement;

public record AddPopulationResult(
    int requestedAmount,
    int addedAmount,
    boolean hasAddedEveryone,
    int overflow,
    int capacityRemaining
) { }
