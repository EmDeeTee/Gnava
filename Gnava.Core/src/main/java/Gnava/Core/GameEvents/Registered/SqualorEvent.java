package Gnava.Core.GameEvents.Registered;

import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import Gnava.GameApi.GameEvents.Settlements.ISettlementEventContext;
import Gnava.GameApi.GameEvents.Settlements.SettlementView;
import Gnava.GameApi.GameEvents.Settlements.SettlementWealthLevel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.random.RandomGenerator;

@Component
public final class SqualorEvent implements IGameEvent<ISettlementEventContext> {
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
    public boolean canTrigger(ISettlementEventContext context) {
        return context.settlement().wealthLevel() != SettlementWealthLevel.DESTITUTE;
    }

    @Override
    public GameEventResult trigger(ISettlementEventContext context, RandomGenerator random) {
        SettlementView settlement = context.settlement();
        String[] reasons = {
            "Because of bad budget management, %s is now in squalor".formatted(settlement.name()),
            "%s got hit with a Squalormelon".formatted(settlement.name())
        };

        context.setWealthLevel(SettlementWealthLevel.DESTITUTE);

        return GameEventResult.translated(Map.of(
            "name", settlement.name(),
            "reason", reasons[random.nextInt(reasons.length)]
        ));
    }
}
