package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Settlement;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.Map;
import java.util.random.RandomGenerator;

public final class NosferatuEvent implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "nosferatu");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.nosferatu"
    ).weight(0.1).build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        return context.currentDay() >= 30
            && context.settlement().getTotalPopulation() > 100;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        Settlement settlement = context.settlement();
        int damage = random.nextInt(1, Math.min(200, settlement.getTotalPopulation()) + 1);
        settlement.addPopulation(-damage);

        return GameEventResult.translated(Map.of(
            "name", settlement.getName(),
            "amount", String.valueOf(damage),
            "plural", settlement.getPopulationType().plural()
        ));
    }
}
