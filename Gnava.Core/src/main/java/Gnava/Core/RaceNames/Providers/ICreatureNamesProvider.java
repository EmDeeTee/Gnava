package Gnava.Core.RaceNames.Providers;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.Core.RaceNames.CreatureName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICreatureNamesProvider {
    List<CreatureName> getCreatureNames();
    SettlementPopulationType getType();
}
