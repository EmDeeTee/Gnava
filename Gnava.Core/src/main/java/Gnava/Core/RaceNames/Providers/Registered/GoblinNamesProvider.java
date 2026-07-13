package Gnava.Core.RaceNames.Providers.Registered;

import Gnava.Core.Models.Settlement.Enums.SettlementPopulationType;
import Gnava.Core.RaceNames.CreatureName;
import Gnava.Core.RaceNames.Providers.AbstractNamesProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoblinNamesProvider extends AbstractNamesProvider {
    @Override
    public List<CreatureName> getCreatureNames() {
        return List.of(
            name("Grub", "Rotfang"),
            name("Snik", "Crookedtooth"),
            name("Ruk", "Muckfoot"),
            name("Zag", "Blackear"),
            name("Skrit", "Bogsnout"),
            name("Mog", "Gutripper"),
            name("Krik", "Bonechewer"),
            name("Drik", "Wartnose"),
            name("Grek", "Mudbelly"),
            name("Nib", "Sharpshiv"),
            name("Urg", "Stinkhide"),
            name("Vrak", "Scarsnout"),
            name("Thok", "Ironjaw"),
            name("Splug", "Ratcatcher"),
            name("Brak", "Croweye"),
            name("Gash", "Skullcracker"),
            name("Rikk", "Filthclaw"),
            name("Snag", "Crookfinger"),
            name("Krog", "Ashhide"),
            name("Zruk", "Mossback")
        );
    }

    @Override
    public SettlementPopulationType getType() {
        return SettlementPopulationType.GOBLIN;
    }
}
