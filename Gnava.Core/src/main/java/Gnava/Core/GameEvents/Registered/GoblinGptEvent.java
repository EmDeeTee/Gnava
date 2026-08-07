package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.Map;
import java.util.random.RandomGenerator;

public final class GoblinGptEvent implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "goblin");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.goblin"
    ).weight(0.02).oneTime().build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        return context.currentDay() >= 30
            && context.settlement().getPopulationType() == SettlementPopulationType.GOBLIN;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        context.settlement().setWealthLevel(SettlementWealthLevel.AFFLUENT);
        context.settlement().setMaxPopulation(context.settlement().getMaxPopulation() + 350);

        return GameEventResult.translated(Map.of("name", context.settlement().getName()));
    }
}
