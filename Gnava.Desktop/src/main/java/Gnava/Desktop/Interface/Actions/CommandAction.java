package Gnava.Desktop.Interface.Actions;

import Gnava.Core.Commands.Command;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public abstract class CommandAction<I, O> extends AbstractAction {
    protected final Command<I, O> command;

    public CommandAction(
        Command<I, O> command
    ) {
        this.command = command;
    }

    public final void actionPerformed(ActionEvent e) {
        execute();
    }

    public void execute() {
        getInput().ifPresent(input -> {
            O result = command.execute(input);
            handleResult(result);
        });
    }

    protected abstract Optional<I> getInput();

    protected void handleResult(O result){
        
    }
}