package Gnava.Game.Events.Simulation;

public abstract class GameEvent {
    private final String title;
    private final String description;

    public GameEvent(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public GameEvent(String title) {
        this(title, "");
    }

    @Override
    public String toString() {
        return title;
    }
}
