package Gnava.Core.Events;

public record ExecutedGameEvent(
    String title,
    String description,
    boolean storyEvent,
    int happenedOnDay,
    boolean isMinor
) {
    @Override
    public String toString() {
        return title;
    }
}
