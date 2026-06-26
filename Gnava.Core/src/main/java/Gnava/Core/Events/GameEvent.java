package Gnava.Core.Events;

public record GameEvent(String title, String description, boolean storyEvent) {
    @Override
    public String toString() {
        return title;
    }
}
