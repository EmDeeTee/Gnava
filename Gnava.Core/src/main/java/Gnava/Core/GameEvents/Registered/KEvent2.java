package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventResult;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

import java.util.Map;
import java.util.random.RandomGenerator;

public final class KEvent2 implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "k_event_2");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.k_event_2"
    ).weight(0.04)
        .oneTime()
        .storyEvent()
        .requires(KEvent.ID)
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(SettlementEventContext context) {
        return context.settlement().getTotalPopulation() > 100;
    }

    @Override
    public GameEventResult trigger(SettlementEventContext context, RandomGenerator random) {
        context.settlement().addPopulation(-100);
        return GameEventResult.translated(Map.of(
            "name", context.settlement().getName(),
            "plural", context.settlement().getPopulationType().plural()
        ));
    }
}
