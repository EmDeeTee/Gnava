package Gnava.Core.Commands;

// TODO: Actually... Aren't Java Swing Actions just commands and am I just reinventing the wheel adding unnecessary abstraction?
@FunctionalInterface
public interface Command<T> {
    void execute(T param);
}
