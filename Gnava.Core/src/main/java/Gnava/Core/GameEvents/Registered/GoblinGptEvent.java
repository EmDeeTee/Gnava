package Gnava.Core.GameEvents.Registered;

import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.SettlementPopulationType;
import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class GoblinGptEvent implements IGameEvent<ISettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "goblin");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.goblin"
    ).weight(0.02)
        .oneTime()
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(ISettlementEventContext context) {
        return context.currentDay() >= 30 && context.settlement().populationType() == SettlementPopulationType.GOBLIN;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        context.setWealthLevel(SettlementWealthLevel.AFFLUENT);
        context.expandPopulationCapacity(350);

        return GameEventResult.translated(Map.of("name", context.settlement().name()));
    }
}
