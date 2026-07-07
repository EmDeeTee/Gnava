package Gnava.Core.Events;

public record ExecutedGameEvent(
    String title,
    String description,
    boolean storyEvent,
    int happenedOnDay
) {
    @Override
    public String toString() {
        return title;
    }
}
