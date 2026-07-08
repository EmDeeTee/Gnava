package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.WorldEventContext;

public final class MinimumWorldPopulationCondition implements EventCondition<WorldEventContext> {
    private final int minimumPopulation;

    public MinimumWorldPopulationCondition(int minimumPopulation) {
        this.minimumPopulation = minimumPopulation;
    }

    @Override
    public boolean isSatisfied(WorldEventContext eventContext) {
        return eventContext.getStatistics().population() >= minimumPopulation;
    }
}
