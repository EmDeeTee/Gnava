package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;
import org.springframework.stereotype.Service;

@Service
public final class KEvent extends AbstractGameEvent {
    @Override
    protected EventCondition[] conditions() {
        // NOTE: I kinda don't like how it forces the method to call something twice
        return new EventCondition[] {
            ctx -> ctx.getWorldStatisticsProvider().getWorldStatistics().population() > 2000 &&
            ctx.getWorldStatisticsProvider().getWorldStatistics().settlementCount() >= 2
        };
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
    protected String resolveTitle(EventContext context) {
        return "A strange figure seen on the horizon";
    }

    @Override
    protected String resolveDescription(EventContext context) {
        return "..æ.";
    }
}
