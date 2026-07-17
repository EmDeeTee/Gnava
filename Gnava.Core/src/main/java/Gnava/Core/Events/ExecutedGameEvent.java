package Gnava.Core.Events;

public record ExecutedGameEvent(
    String title,
    String description,
    boolean storyEvent,
    int happenedOnDay,
    boolean isMinor,
    TranslationData translationData
) {
    @Override
    public String toString() {
        return title;
    }

    public String toStringNew() {
        return translationData.titleKey();
    }
}
