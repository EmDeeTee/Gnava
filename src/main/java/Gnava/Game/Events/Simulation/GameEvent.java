package Gnava.Game.Events.Simulation;

public abstract class GameEvent {
    private final String title;
    private final String description;

    public GameEvent(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void happen() {

    }
}
