package Gnava.Game.Events.Simulation;

@FunctionalInterface
public interface EventAction {
    void execute(EventContext ctx);
}
