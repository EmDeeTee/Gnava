package Gnava.Game.Events.Conditions;

import Gnava.Game.GameState;

public class FiresOnceEventCondition implements EventCondition {
    @Override
    public boolean isSatisfied() {
        return true;
        //return GameState.getInstance().getGameEventsManager().hasEventHappened();
    }
}
