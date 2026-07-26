package Gnava.Core.CommandHandlers;

@FunctionalInterface
public interface ICommand<I, O> {
    O execute(I input);
}