package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Registered.Gnomerooms;
import Gnava.Core.GameEvents.Registered.GoblinGptEvent;
import Gnava.Core.GameEvents.Registered.KEvent;
import Gnava.Core.GameEvents.Registered.KEvent2;
import Gnava.Core.GameEvents.Registered.KEvent3;
import Gnava.Core.GameEvents.Registered.NosferatuEvent;
import Gnava.Core.GameEvents.Registered.PopulationGrowthEvent;
import Gnava.Core.GameEvents.Registered.PopulationTotalGrowthEvent;
import Gnava.Core.GameEvents.Registered.SqualorEvent;
import Gnava.Core.GameEvents.Registered.WizardEvent;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.List;

/** The explicit catalog of events shipped with the base game. */
public final class VanillaEventModule {
    public static List<IGameEvent<?>> events(CreatureNameGenerator creatureNameGenerator) {
        return List.of(
            new Gnomerooms(creatureNameGenerator),
            new GoblinGptEvent(),
            new KEvent(),
            new KEvent2(),
            new KEvent3(),
            new NosferatuEvent(),
            new PopulationGrowthEvent(),
            new PopulationTotalGrowthEvent(),
            new SqualorEvent(),
            new WizardEvent()
        );
    }
}
