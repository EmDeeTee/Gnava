package Gnava.Game.Events;

@FunctionalInterface
public interface EventAction {
    void execute(EventContext ctx);
}
