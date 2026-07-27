package Gnava.Core.GameEvents.Conditions.Universal;

import Gnava.Core.GameEvents.Conditions.EventCondition;
import Gnava.Core.GameEvents.Contexts.EventContext;

public final class MinimumWorldPopulationCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumPopulation;

    public MinimumWorldPopulationCondition(int minimumPopulation) {
        this.minimumPopulation = minimumPopulation;
    }

    @Override
    public boolean isSatisfied(C context) {
        return context.getWorldStatistics().population() >= minimumPopulation;
    }
}
