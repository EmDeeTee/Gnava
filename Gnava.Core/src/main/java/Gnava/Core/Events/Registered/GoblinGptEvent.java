package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.SettlementPopulationTypeCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public final class GoblinGptEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new MinimumGameDayCondition<>(30),
            new SettlementPopulationTypeCondition(SettlementPopulationType.GOBLIN)
        );
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.goblin.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.goblin.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        return Map.ofEntries(
            Map.entry("name", context.getRandomTargetSettlement().getName())
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        context.getRandomTargetSettlement().setWealthLevel(SettlementWealthLevel.AFFLUENT);
        context.getRandomTargetSettlement().setMaxPopulation(context.getRandomTargetSettlement().getMaxPopulation() + 350);
    }

    @Override
    public boolean firesOnce() {
        return true;
    }

    @Override
    public float probability() {
        return 0.02f;
    }
}
