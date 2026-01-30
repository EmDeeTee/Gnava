package Gnava.Game.Events.Simulation;

import Gnava.Game.Events.Conditions.EventCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GameEventBuilder {
    private String title = "";
    private String description = "";
    private final List<EventCondition> conditions = new ArrayList<>();
    private EventAction action = ctx -> { };
    private boolean firesOnce = false;
    private float probability = 1.0f;
    private boolean isStoryEvent = false;

    public GameEventBuilder withTitle(String t) {
        this.title = t;
        return this;
    }

    public GameEventBuilder withDescription(String d) {
        this.description = d;
        return this;
    }

    public GameEventBuilder when(EventCondition cond) {
        this.conditions.add(cond);
        return this;
    }

    public GameEventBuilder whenAll(EventCondition... conds) {
        Collections.addAll(this.conditions, conds);
        return this;
    }

    public GameEventBuilder action(EventAction act) {
        this.action = act;
        return this;
    }

    public GameEventBuilder once() {
        this.firesOnce = true;
        return this;
    }

    public GameEventBuilder probability(float p) {
        this.probability = p;
        return this;
    }

    public GameEventBuilder isStoryEvent() {
        isStoryEvent = true;
        return this;
    }

    public GameEvent build() {
        return new GameEvent(
            title,
            description,
            conditions.toArray(new EventCondition[0]),
            action,
            firesOnce,
            probability,
            isStoryEvent
        );
    }
}
