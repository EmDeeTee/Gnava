package Gnava.Game.Events;

import Gnava.Game.Events.Conditions.EventCondition;

public abstract class AbstractGameEventDefinition implements GameEventDefinition {
    @Override
    public final GameEvent happen(EventContext context) {
        prepare(context);
        String title = resolveTitle(context);
        String description = resolveDescription(context);
        apply(context);
        return new GameEvent(title, description, isStoryEvent());
    }

    @Override
    public final boolean canRun(EventContext context) {
        for (EventCondition condition : conditions()) {
            if (!condition.isSatisfied(context)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean firesOnce() {
        return false;
    }

    @Override
    public float probability() {
        return 1.0f;
    }

    @Override
    public boolean isStoryEvent() {
        return false;
    }

    protected EventCondition[] conditions() {
        return new EventCondition[0];
    }

    protected void prepare(EventContext context) { }

    protected String resolveDescription(EventContext context) {
        return "";
    }

    protected void apply(EventContext context) { }

    protected abstract String resolveTitle(EventContext context);
}
