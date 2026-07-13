package Gnava.Core.Settlements.NameGenerator.Providers;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;

import java.util.List;

public interface ISettlementNamesProvider {
    List<String> getSettlementNames();
    SettlementPopulationType getType();
}
