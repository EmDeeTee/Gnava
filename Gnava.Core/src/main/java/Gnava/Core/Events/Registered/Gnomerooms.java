package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.Events.Conditions.Settlement.SettlementPopulationTypeCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.RaceNames.DefaultCreatureName;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public final class Gnomerooms extends AbstractGameEventDefinition<SettlementEventContext> {
    private final CreatureNameGenerator creatureNameGenerator;

    public Gnomerooms(CreatureNameGenerator creatureNameGenerator) {
        this.creatureNameGenerator = creatureNameGenerator;
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        context.set("person", creatureNameGenerator
            .generate(context.getRandomTargetSettlement().getPopulationType())
            .creatureName()
            .orElse(DefaultCreatureName.get())
            .fullName());
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events.gnomerooms.title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events.gnomerooms.description";
    }

    @Override
    protected Map<String, String> getTranslationContext(SettlementEventContext context) {
        return Map.ofEntries(
            Map.entry("person", context.get("person", String.class).orElseThrow()),
            Map.entry("name", context.getRandomTargetSettlement().getName())
        );
    }

    @Override
    public float probability() {
        return 0.04f;
    }

    @Override
    protected List<EventCondition<SettlementEventContext>> conditions() {
        return List.of(
            new SettlementPopulationTypeCondition(SettlementPopulationType.GNOME),
            new MinimumSettlementPopulationCondition(1),
            new MinimumGameDayCondition<>(10)
        );
    }

    @Override
    protected void apply(SettlementEventContext context) {
        Settlement target = context.getRandomTargetSettlement();
        target.addPopulation(-1);
    }
}
