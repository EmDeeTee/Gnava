package Gnava.GameApi.GameEvents;

import java.util.ArrayList;
import java.util.List;

public record EventSpecification(
    GameEventId id,
    GameEventScope scope,
    String translationKey,
    double weight,
    boolean repeatable,
    boolean storyEvent,
    boolean minor,
    List<GameEventId> prerequisites
) {
    public EventSpecification {
        translationKey = translationKey.trim();
        prerequisites = List.copyOf(prerequisites);

        if (translationKey.isEmpty()) {
            throw new IllegalArgumentException("Event translation key cannot be empty");
        }
        if (!Double.isFinite(weight) || weight <= 0.0) {
            throw new IllegalArgumentException("Event weight must be a positive finite number");
        }
    }

    public static Builder builder(
        GameEventId id,
        GameEventScope scope,
        String translationKey
    ) {
        return new Builder(id, scope, translationKey);
    }

    public static final class Builder {
        private final GameEventId id;
        private final GameEventScope scope;
        private final String translationKey;
        private double weight = 1.0;
        private boolean repeatable = true;
        private boolean storyEvent;
        private boolean minor;
        private final List<GameEventId> prerequisites = new ArrayList<>();

        private Builder(GameEventId id, GameEventScope scope, String translationKey) {
            this.id = id;
            this.scope = scope;
            this.translationKey = translationKey;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder oneTime() {
            repeatable = false;
            return this;
        }

        public Builder storyEvent() {
            storyEvent = true;
            return this;
        }

        public Builder minor() {
            minor = true;
            return this;
        }

        public Builder requires(GameEventId... eventIds) {
            prerequisites.addAll(List.of(eventIds));
            return this;
        }

        public EventSpecification build() {
            return new EventSpecification(
                id,
                scope,
                translationKey,
                weight,
                repeatable,
                storyEvent,
                minor,
                prerequisites
            );
        }
    }
}
