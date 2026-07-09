package Gnava.Core.RaceNames.Providers.Registered;

import Gnava.Core.Models.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.CreatureName;
import Gnava.Core.RaceNames.Providers.AbstractNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class GnomeNamesProvider extends AbstractNamesProvider {
    @Override
    public List<CreatureName> getCreatureNames() {
        return List.of(
            name("Gnome", "Gnomington"),
            name("Bimble", "Thistlewick"),
            name("Pip", "Coppernose"),
            name("Tovin", "Gearwhistle"),
            name("Nib", "Fizzlebloom"),
            name("Wendel", "Bramblefoot"),
            name("Tobin", "Clockroot")
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.GNOME;
    }
}
