package Gnava.Core.GameEvents.Registered;

import Gnava.Core.GameEvents.AbstractGameEvent;
import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.GameEvents.Contexts.SettlementEventContext;
import Gnava.Core.GameEvents.IGameEventDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public final class KEvent2 extends AbstractGameEvent<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumSettlementPopulationCondition(100)
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().addPopulation(-100);
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.k_event_2.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.k_event_2.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        return Map.ofEntries(
            Map.entry("name", context.getRandomTargetSettlement().getName()),
            Map.entry("plural", context.getRandomTargetSettlement().getPopulationType().plural())
        );
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
        return 0.04f;
    }

    @Override
    public List<Class<? extends IGameEventDefinition<?>>> prerequisites() {
        return List.of(KEvent.class);
    }
}
