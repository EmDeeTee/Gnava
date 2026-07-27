package Gnava.Core.GameEvents;

public record ExecutedGameEvent(
    @Deprecated String title,
    @Deprecated String description,
    boolean storyEvent,
    int happenedOnDay,
    boolean isMinor,
    TranslationData translationData
) {
    @Override
    public String toString() {
        return title;
    }
}
