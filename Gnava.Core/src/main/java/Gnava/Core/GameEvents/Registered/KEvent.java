package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.Contexts.WorldEventContext;
import Gnava.GameApi.GameEvents.EventSpecification;
import Gnava.GameApi.GameEvents.GameEventId;
import Gnava.GameApi.GameEvents.GameEventScope;
import Gnava.GameApi.GameEvents.IGameEvent;
import org.springframework.stereotype.Component;

@Component
public final class KEvent implements IGameEvent<WorldEventContext> {
    public static final GameEventId ID = new GameEventId("gnava", "k_event");

    private static final EventSpecification SPEC = EventSpecification.builder(
        ID,
        GameEventScope.WORLD,
        "events.k_event"
    ).oneTime().storyEvent().build();

    @Override
    public EventSpecification specification() {
        return SPEC;
    }

    @Override
    public boolean canTrigger(WorldEventContext context) {
        return context.currentDay() >= 100
            && context.worldStatistics().population() >= 2000
            && context.worldStatistics().settlementCount() >= 2;
    }
}
