package Gnava.Core.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.function.Supplier;

@Deprecated
public abstract class CommandAction<I, O> extends AbstractAction {
    private final Command<I, O> command;
    private final Supplier<I> inputSupplier;

    public CommandAction(
        Command<I, O> command,
        Supplier<I> inputSupplier
    ) {
        this.command = command;
        this.inputSupplier = inputSupplier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        execute();
    }

    public void execute() {
        O result = command.execute(inputSupplier.get());
        handleResult(result);
    }

    protected abstract void handleResult(O result);
}