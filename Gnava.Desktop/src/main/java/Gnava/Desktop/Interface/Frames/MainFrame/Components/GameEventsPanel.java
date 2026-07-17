package Gnava.Desktop.Interface.Frames.MainFrame.Components;

import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Desktop.Facades.Translation;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Renderers.GameEventListRenderer;
import Gnava.Desktop.Interface.Translations.TranslationKey;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class GameEventsPanel extends JPanel {
    private final JFrame parent;

    private final DefaultListModel<ExecutedGameEvent> eventListModel = new DefaultListModel<>();
    private final JList<ExecutedGameEvent> eventList = new JList<>(eventListModel);

    public GameEventsPanel(JFrame parent) {
        super(new BorderLayout(5, 5));
        this.parent = parent;

        JButton filterButton = new JButton("Filter");
        filterButton.setEnabled(false); // Placeholder

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(filterButton);

        eventList.setCellRenderer(new GameEventListRenderer());
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
                new PlaintextPopup(parent, selected.description(), "Happened on day %s".formatted(selected.happenedOnDay())).show();
                eventList.clearSelection();
            }
        }
    }
}
