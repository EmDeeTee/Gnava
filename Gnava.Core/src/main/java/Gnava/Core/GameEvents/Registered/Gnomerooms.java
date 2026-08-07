package Gnava.Core.GameEvents.Registered;

import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.RaceNames.DefaultCreatureName;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class Gnomerooms implements IGameEvent<ISettlementEventContext> {
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
    public boolean canTrigger(ISettlementEventContext context) {
        SettlementView settlement = context.settlement();
        return context.currentDay() >= 10
            && settlement.populationType() == SettlementPopulationType.GNOME
            && settlement.totalPopulation() > 1;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        SettlementView settlement = context.settlement();
        String person = creatureNameGenerator
            .generate(settlement.populationType(), random)
            .creatureName()
            .orElse(DefaultCreatureName.get())
            .fullName();

        context.addPopulation(-1);

        return GameEventResult.translated(Map.of(
            "person", person,
            "name", settlement.name()
        ));
    }
}
