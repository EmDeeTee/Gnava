package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumWorldPopulationCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumWorldSettlementsCondition;
import Gnava.Core.Events.Contexts.WorldEventContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class KEvent extends AbstractGameEventDefinition<WorldEventContext> {
    @Override
    protected List<EventCondition<WorldEventContext>> conditions() {
        return List.of(
            new MinimumWorldPopulationCondition<>(2000),
            new MinimumWorldSettlementsCondition<>(2),
            new MinimumGameDayCondition<>(100)
        );
    }

    @Override
    public boolean firesOnce() {
        return true;
    }

    @Override
    public boolean isStoryEvent() {
        return true;
    }

    @Override
    protected String resolveTitle(WorldEventContext context) {
        return "A strange figure seen on the horizon";
    }

    @Override
    protected String resolveDescription(WorldEventContext context) {
        return "..æ.";
    }
}
