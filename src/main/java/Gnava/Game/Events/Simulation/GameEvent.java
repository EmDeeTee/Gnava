package Gnava.Game.Events.Simulation;

import Gnava.Game.Events.Conditions.EventCondition;
import Gnava.Game.GameState;

import java.util.Arrays;

public abstract class GameEvent {
    private final String title;
    private String description;
    private final EventCondition[] conditions;
    private final boolean firesOnce;

    public GameEvent(String title, String description, EventCondition[] conditions, boolean firesOnce) {
        this.title = title;
        this.description = description;
        this.conditions = conditions;
        this.firesOnce = firesOnce;
    }

    public abstract void happen();

    @Override
    public String toString() {
        return title;
    }

    public boolean canRun() {
        return Arrays.stream(conditions).allMatch(EventCondition::isSatisfied) && !GameState.getInstance().getGameEventsManager().hasEventHappened(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public EventCondition[] getConditions() {
        return conditions;
    }
}
