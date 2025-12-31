package Gnava.Game.Events.Simulation;

public abstract class GameEvent {
    private final String title;
    private String description;

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

    public void setDescription(String desc) {
        description = desc;
    }

    public void happen() {

    }
}
