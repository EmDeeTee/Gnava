package Gnava.Desktop.Interface.Renderers;

import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Desktop.Interface.Renderers.Exceptions.UnexpectedObjectTypeException;

import javax.swing.*;
import java.awt.*;

public class GameEventListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
        JList<?> list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus
    ) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (index == 0) {
            setFont(getFont().deriveFont(Font.BOLD, 14f));
        } else {
            setFont(getFont().deriveFont(Font.PLAIN, 14f));
        }

        ExecutedGameEvent executedGameEvent = (value instanceof ExecutedGameEvent) ? (ExecutedGameEvent) value : null;
        if (executedGameEvent == null) {
            throw new UnexpectedObjectTypeException("GameEventListRenderer got a non GameObject value");
        }

        if (executedGameEvent.storyEvent()) {
            setFont(getFont().deriveFont(Font.BOLD, 14f));
            setForeground(Color.MAGENTA);
        }
        if (executedGameEvent.isMinor()) {
            setFont(getFont().deriveFont(Font.BOLD, 12f));
            setForeground(Color.GRAY);
        }

        return this;
    }
}
