package Gnava.Core.Events.Conditions;

import Gnava.Core.Events.Contexts.EventContext;

public final class MinimumGameDayCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumGameDay;

    public MinimumGameDayCondition(int minimumGameDay) {
        this.minimumGameDay = minimumGameDay;
    }

    @Override
    public boolean isSatisfied(C eventContext) {
        return eventContext.getGameState().getCurrentDay() >= minimumGameDay;
    }
}
