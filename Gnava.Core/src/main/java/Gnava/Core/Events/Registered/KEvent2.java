package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import Gnava.Core.Events.TranslationData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class KEvent2 extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumSettlementPopulationCondition(100)
        );
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "There is talk of a very dangerous person roaming the realm at night.<br><br>It got so bad that 100 %s escaped from %s."
            .formatted(
                context.getRandomTargetSettlement().getPopulationType().plural(),
                context.getRandomTargetSettlement().getName()
            );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().addPopulation(-100);
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Rumors of ruin";
    }

    @Override
    protected TranslationData getTranslationData() {
        return null;
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
