package Gnava.Core.GameEvents.Registered;

import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class NosferatuEvent implements IGameEvent<ISettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "nosferatu");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.nosferatu"
    ).weight(0.1)
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(ISettlementEventContext context) {
        return context.currentDay() >= 30
            && context.settlement().totalPopulation() > 100;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        SettlementView settlement = context.settlement();
        int damage = random.nextInt(1, Math.min(200, settlement.totalPopulation()) + 1);
        context.addPopulation(-damage);

        return GameEventResult.translated(Map.of(
            "name", settlement.name(),
            "amount", String.valueOf(damage),
            "plural", settlement.populationType().plural()
        ));
    }
}
