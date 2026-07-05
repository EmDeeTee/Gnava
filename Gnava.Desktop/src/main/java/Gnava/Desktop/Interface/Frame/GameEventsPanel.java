package Gnava.Desktop.Interface.Frame;

import Gnava.Core.Events.GameEvent;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Renderers.GameEventListRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class GameEventsPanel extends JPanel {
    private final JFrame parent;

    private final DefaultListModel<GameEvent> eventListModel = new DefaultListModel<>();
    private final JList<GameEvent> eventList = new JList<>(eventListModel);

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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Events"));

        add(toolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addEvent(GameEvent event) {
        eventListModel.add(0, event);
    }

    public void clear() {
        eventListModel.clear();
    }

    private void onEventSelected(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            GameEvent selected = eventList.getSelectedValue();
            if (selected != null) {
                new PlaintextPopup(parent, selected.description()).show();
                eventList.clearSelection();
            }
        }
    }
}
