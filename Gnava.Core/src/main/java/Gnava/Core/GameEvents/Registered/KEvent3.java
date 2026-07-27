package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.AbstractGameEventDefinition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.GameEvents.IGameEventDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class KEvent3 extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected String getTitleTranslationKey() {
        return "events.k_event_3.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.k_event_3.description";
    }

    @Override
    public boolean isStoryEvent() {
        return true;
    }

    @Override
    public boolean firesOnce() {
        return true;
    }

    @Override
    public float probability() {
        return 0.03f;
    }

    @Override
    public List<Class<? extends IGameEventDefinition<?>>> prerequisites() {
        return List.of(KEvent2.class);
    }
}
