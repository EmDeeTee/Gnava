package Gnava.Core.Settlements.NameGenerator.Providers;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;

import java.util.List;

public interface ISettlementNamesProvider {
    List<String> getSettlementNames();
    SettlementPopulationType getType();
}
