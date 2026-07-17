package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Conditions.Settlement.MinimumSettlementPopulationCondition;
import Gnava.Core.Events.Conditions.Settlement.SettlementPopulationTypeCondition;
import Gnava.Core.Events.Conditions.Universal.MinimumGameDayCondition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.TranslationData;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.RaceNames.CreatureNameGenerator;
import Gnava.Core.RaceNames.DefaultCreatureName;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class Gnomerooms extends AbstractGameEventDefinition<SettlementEventContext> {
    private final CreatureNameGenerator creatureNameGenerator;

    public Gnomerooms(CreatureNameGenerator creatureNameGenerator) {
        this.creatureNameGenerator = creatureNameGenerator;
    }

    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "%s from %s fell into the gnomerooms".formatted(
            creatureNameGenerator
                .generate(context.getRandomTargetSettlement().getPopulationType())
                .creatureName()
                .orElse(DefaultCreatureName.get())
                .fullName(),
            context.getRandomTargetSettlement().getName()
        );
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Gnomerooms incident";
    }

    @Override
    protected TranslationData getTranslationData() {
        return null;
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
