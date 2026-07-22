package Gnava.Core.Server.Endpoints.Responces;

public record StatisticsResponse(
    int totalPopulation,
    int playerBodyCount,
    int settlementCount,
    int totalSpellsCasted
) { }
