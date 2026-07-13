package Gnava.Core.Settlements.NameGenerator.Providers.Registered;

import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.NameGenerator.Providers.ISettlementNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class DwarfSettlementNamesProvider implements ISettlementNamesProvider {
    @Override
    public List<String> getSettlementNames() {
        return List.of(
            "Dwarf Fortress",
            "Ironforge",
            "Khaz-Modan",
            "Stonehelm",
            "Deephearth",
            "Thunderdeep",
            "Goldvein",
            "Anvilpeak",
            "Rockbreaker",
            "Copperdelve"
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.DWARF;
    }
}
