package Gnava.Interface.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.function.Supplier;

public class CommandAction<T> extends AbstractAction {
    private final Command<T> command;
    private final Supplier<Optional<T>> inputSupplier;

    public CommandAction(Command<T> command, Supplier<Optional<T>> inputSupplier) {
        this.command = command;
        this.inputSupplier = inputSupplier;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputSupplier.get().ifPresent(command::execute);
    }
}
