package Gnava.Game.Commands;

public interface Command<T> {
    void execute(T param);
}
