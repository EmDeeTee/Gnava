package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;

public final class KEvent3 implements IGameEvent<SettlementEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "k_event_3");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.SETTLEMENT,
        "events.k_event_3"
    ).weight(0.03)
        .oneTime()
        .storyEvent()
        .requires(KEvent2.ID)
        .build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }
}
