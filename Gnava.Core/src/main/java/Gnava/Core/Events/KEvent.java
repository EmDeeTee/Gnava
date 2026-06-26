package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;

public final class KEvent extends AbstractGameEvent {
    private KEvent() { }

    public static KEvent create() {
        return new KEvent();
    }

    @Override
    protected EventCondition[] conditions() {
        return new EventCondition[] {
            ctx -> ctx.getGameState().getWorldStatistics().population() > 2000 &&
            ctx.getGameState().getWorldStatistics().settlementCount() >= 2
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
