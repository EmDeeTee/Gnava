package Gnava.Game.Events.Simulation;

public class NothingEvent extends GameEvent {
    public NothingEvent(String title, String description) {
        super(title, description, null, false);
    }

    @Override
    public void happen() { }
}
