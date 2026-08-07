package Gnava.Core.GameEvents;

import Gnava.GameApi.GameEvents.GameEventId;

public record ExecutedGameEvent(
    GameEventId id,
    String fallbackTitle,
    String fallbackDescription,
    boolean storyEvent,
    int happenedOnDay,
    boolean isMinor,
    TranslationData translationData
) {
    @Override
    public String toString() {
        return fallbackTitle;
    }
}
