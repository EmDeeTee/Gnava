package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class SuppliedCommandAction<I, O> extends CommandAction<I, O> {
    private final Supplier<I> supplier;

    public SuppliedCommandAction(
        Command<I, O> command,
        Supplier<I> supplier
    ) {
        super(command);
        this.supplier = supplier;
    }

    @Override
    protected Optional<I> getInput() {
        return Optional.ofNullable(supplier.get());
    }
}