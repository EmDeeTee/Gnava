package Gnava.Interface.Commands;

public interface Command<T> {
    void execute(T param);
}
