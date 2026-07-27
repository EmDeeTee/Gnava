package Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Renderers;

import Gnava.Core.GameEvents.ExecutedGameEvent;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Renderers.Exceptions.UnexpectedObjectTypeException;
import Gnava.Desktop.Interface.Translations.Translator;

import javax.swing.*;
import java.awt.*;

@org.springframework.stereotype.Component
public class GameEventListRenderer extends DefaultListCellRenderer {
    private final Translator translator;

    public GameEventListRenderer(Translator translator) {
        this.translator = translator;
    }

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

        String titleKey = executedGameEvent.translationData().titleKey();
        String text = (titleKey == null || titleKey.isBlank())
            ? executedGameEvent.title()
            : translator.t(titleKey, executedGameEvent.translationData().context());

        setText(text);

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
