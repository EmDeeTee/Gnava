package Gnava.Core.Events;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractGameEventDefinition<C extends EventContext> implements IGameEventDefinition<C> {
    @Override
    public final ExecutedGameEvent happen(C context) {
        prepare(context);
        apply(context);
        String title = resolveTitle(context);
        String description = resolveDescription(context);
        return new ExecutedGameEvent(
            title,
            description,
            isStoryEvent(),
            context.getGameState().getCurrentDay(),
            isMinor(),
            translationData(context)
        );
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
    public List<Class<? extends IGameEventDefinition<?>>> prerequisites() {
        return Collections.emptyList();
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

    @Deprecated
    protected String resolveDescription(C context) {
        return "";
    }

    @Deprecated
    protected String resolveTitle(C context) {
        return "";
    }

    protected TranslationData translationData(C context) {
        return new TranslationData(
            getTitleTranslationKey(),
            getDescriptionTranslationKey(),
            getTranslationContext(context)
        );
    }

    // TODO: I want this to work more like Paradox's engine, where events define only one translation key, ex. events.growth_event
    // and then the engine automatically figures out that this event also has keys for .title and .description
    protected abstract String getTitleTranslationKey();

    protected abstract String getDescriptionTranslationKey();

    protected Map<String, String> getTranslationContext(C context) {
        return Collections.emptyMap();
    }
}
