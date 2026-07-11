package Gnava.Core.Events.Conditions.Universal;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

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
