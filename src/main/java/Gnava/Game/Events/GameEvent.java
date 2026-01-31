package Gnava.Game.Events;

import Gnava.Game.Events.Conditions.EventCondition;

public class GameEvent implements Cloneable {
    private String title;
    private final EventTitleProvider titleProvider;
    private final String description;
    private final EventCondition[] conditions;
    private final EventAction prepareAction;
    private final EventAction action;
    private final boolean firesOnce;
    private final float probability;
    private final boolean isStoryEvent;

    public GameEvent(
        String title,
        EventTitleProvider titleProvider,
        String description,
        EventCondition[] conditions,
        EventAction prepareAction,
        EventAction action,
        boolean firesOnce,
        float probability,
        boolean isStoryEvent
    ) {
        this.title = title;
        this.titleProvider = titleProvider;
        this.description = description;
        this.conditions = conditions;
        this.prepareAction = prepareAction;
        this.action = action;
        this.firesOnce = firesOnce;
        this.probability = probability;
        this.isStoryEvent = isStoryEvent;
    }

    public void happen(EventContext context) {
        prepareAction.execute(context);
        title = resolveTitle(context);
        action.execute(context);
    }

    public String resolveTitle(EventContext context) {
        if (titleProvider != null) {
            return titleProvider.getTitle(context);
        }

        return title != null ? title : "Unnamed Event";
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

    @Override
    public GameEvent clone() {
        try {
            GameEvent clone = (GameEvent) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
