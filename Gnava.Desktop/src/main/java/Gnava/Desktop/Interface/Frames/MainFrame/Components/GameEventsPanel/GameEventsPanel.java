package Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel;

import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Desktop.Facades.Translation;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.Renderers.GameEventListRenderer;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

@Component
public class GameEventsPanel extends JPanel {
    private final JFrame parent;

    private final DefaultListModel<ExecutedGameEvent> eventListModel = new DefaultListModel<>();
    private final JList<ExecutedGameEvent> eventList = new JList<>(eventListModel);

    public GameEventsPanel(
        GameEventListRenderer gameEventListRenderer
    ) {
        super(new BorderLayout(5, 5));
        this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);

        JButton filterButton = new JButton("Filter");
        filterButton.setEnabled(false); // Placeholder

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(filterButton);

        eventList.setCellRenderer(gameEventListRenderer);
        eventList.addListSelectionListener(this::onEventSelected);

        JScrollPane scrollPane = new JScrollPane(eventList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(Translation.t(TranslationKey.EVENTS)));

        add(toolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addEvent(ExecutedGameEvent event) {
        eventListModel.add(0, event);
    }

    public void clear() {
        eventListModel.clear();
    }

    private void onEventSelected(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            ExecutedGameEvent selected = eventList.getSelectedValue();
            if (selected != null) {
                // TODO: Now, this would have to call the translation service on the context and the template strings
                new PlaintextPopup(
                    parent,
                    selected.description(),
                    "Happened on day %s".formatted(selected.happenedOnDay())
                ).show();
                eventList.clearSelection();
            }
        }
    }
}
