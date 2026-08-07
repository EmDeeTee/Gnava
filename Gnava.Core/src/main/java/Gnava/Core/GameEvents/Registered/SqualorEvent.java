package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.Map;
import java.util.random.RandomGenerator;

public final class SqualorEvent implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "squalor");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.squalor"
    ).weight(0.05).build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        return context.settlement().getWealthLevel() != SettlementWealthLevel.DESTITUTE;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        Settlement settlement = context.settlement();
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(settlement),
            "%s got hit with a Squalormelon".formatted(settlement)
        };

        settlement.setWealthLevel(SettlementWealthLevel.DESTITUTE);

        return GameEventResult.translated(Map.of(
            "name", settlement.getName(),
            "reason", reasons[random.nextInt(reasons.length)]
        ));
    }
}
