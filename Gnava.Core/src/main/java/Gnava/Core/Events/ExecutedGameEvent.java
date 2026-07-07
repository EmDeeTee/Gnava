package Gnava.Core.Events;

public record ExecutedGameEvent(String title, String description, boolean storyEvent) {
    @Override
    public String toString() {
        return title;
    }
}
