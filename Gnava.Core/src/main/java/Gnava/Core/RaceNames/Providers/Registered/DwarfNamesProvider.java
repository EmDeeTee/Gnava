package Gnava.Core.RaceNames.Providers.Registered;

import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.Core.RaceNames.CreatureName;
import Gnava.Core.RaceNames.Providers.AbstractNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DwarfNamesProvider extends AbstractNamesProvider {
    @Override
    public List<CreatureName> getCreatureNames() {
        return List.of(
            name("Dwarf", "Dwarfy"),
            name("Borin", "Ironbeard"),
            name("Durgan", "Stonehammer"),
            name("Thrain", "Deepforge"),
            name("Haldric", "Emberaxe"),
            name("Rurik", "Blackanvil"),
            name("Varric", "Goldmantle"),
            name("Grun", "Steelbrew"),
            name("Ulgar", "Flintshield")
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.DWARF;
    }
}
