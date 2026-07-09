package Gnava.Core.Commands;

@FunctionalInterface
public interface Command<I, O> {
    O execute(I input);
}