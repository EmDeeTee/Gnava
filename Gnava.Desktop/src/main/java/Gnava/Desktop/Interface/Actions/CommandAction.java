package Gnava.Desktop.Interface.Actions;

import Gnava.Core.CommandHandlers.ICommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public abstract class CommandAction<I, O> extends AbstractAction {
    protected final ICommand<I, O> command;

    public CommandAction(
        ICommand<I, O> command
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