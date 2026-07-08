package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

import java.util.Collections;
import java.util.List;

public abstract class AbstractGameEventDefinition<C extends EventContext> implements IGameEventDefinition<C> {
    @Override
    public final ExecutedGameEvent happen(C context) {
        prepare(context);
        String title = resolveTitle(context);
        String description = resolveDescription(context);
        apply(context);
        return new ExecutedGameEvent(title, description, isStoryEvent(), context.getGameState().getCurrentDay(), isMinor());
    }

    @Override
    public final boolean canRun(C context) {
        for (EventCondition<C> condition : conditions()) {
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

    @Override
    public boolean isMinor() {
        return false;
    }

    protected List<EventCondition<C>> conditions() {
        return Collections.emptyList();
    }

    protected void prepare(C context) { }

    protected void apply(C context) { }

    protected abstract String resolveDescription(C context);

    protected abstract String resolveTitle(C context);
}
