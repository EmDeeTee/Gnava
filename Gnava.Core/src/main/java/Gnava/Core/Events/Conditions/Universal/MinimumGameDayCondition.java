package Gnava.Core.Events.Conditions.Universal;

import Gnava.Core.Events.Conditions.EventCondition;
import Gnava.Core.Events.Contexts.EventContext;

public final class MinimumGameDayCondition<C extends EventContext> implements EventCondition<C> {
    private final int minimumGameDay;

    public MinimumGameDayCondition(int minimumGameDay) {
        this.minimumGameDay = minimumGameDay;
    }

    @Override
    public boolean isSatisfied(C context) {
        return context.getGameState().getCurrentDay() >= minimumGameDay;
    }
}
