package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.RaceNames.DefaultCreatureName;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class Gnomerooms implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "gnomerooms");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.gnomerooms"
    ).weight(0.04).build();

    private final CreatureNameGenerator creatureNameGenerator;

    public Gnomerooms(CreatureNameGenerator creatureNameGenerator) {
        this.creatureNameGenerator = creatureNameGenerator;
    }

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        Settlement settlement = context.settlement();
        return context.currentDay() >= 10
            && settlement.getPopulationType() == SettlementPopulationType.GNOME
            && settlement.getTotalPopulation() > 1;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        Settlement settlement = context.settlement();
        String person = creatureNameGenerator
            .generate(settlement.getPopulationType(), random)
            .creatureName()
            .orElse(DefaultCreatureName.get())
            .fullName();

        settlement.addPopulation(-1);

        return GameEventResult.translated(Map.of(
            "person", person,
            "name", settlement.getName()
        ));
    }
}
