package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

public abstract class AbstractGameEventDefinition implements IGameEvent {
    @Override
    public final ExecutedGameEvent happen(EventContext context) {
        prepare(context);
        String title = resolveTitle(context);
        String description = resolveDescription(context);
        apply(context);
        return new ExecutedGameEvent(title, description, isStoryEvent(), context.getGameState().getCurrentDay());
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

    protected void apply(EventContext context) { }

    protected abstract String resolveDescription(EventContext context);

    protected abstract String resolveTitle(EventContext context);
}
