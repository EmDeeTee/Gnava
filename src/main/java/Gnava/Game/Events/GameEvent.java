package Gnava.Game.Events;

import Gnava.Game.Events.Conditions.EventCondition;

public class GameEvent {
    private final String title;
    private final String description;
    private final EventCondition[] conditions;
    private final EventAction action;
    private final boolean firesOnce;
    private final float probability;
    private final boolean isStoryEvent;

    public GameEvent(
        String title,
        String description,
        EventCondition[] conditions,
        EventAction action,
        boolean firesOnce,
        float probability,
        boolean isStoryEvent
    ) {
        this.title = title;
        this.description = description;
        this.conditions = conditions;
        this.action = action;
        this.firesOnce = firesOnce;
        this.probability = probability;
        this.isStoryEvent = isStoryEvent;
    }

    public void happen(EventContext context) {
        action.execute(context);
    }

    @Override
    public String toString() {
        return title;
    }

    public boolean canRun(EventContext eventContext) {
        for (EventCondition c : conditions) {
            if (!c.isSatisfied(eventContext)) {
                return false;
            }
        }
        return true;
    }

    public String getDescription() {
        return description;
    }

    public EventCondition[] getConditions() {
        return conditions;
    }

    public EventAction getAction() {
        return action;
    }

    public boolean isFiresOnce() {
        return firesOnce;
    }

    public float getProbability() {
        return probability;
    }

    public boolean isStoryEvent() {
        return isStoryEvent;
    }
}
