package Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel;

import Gnava.Core.Events.ExecutedGameEvent;
import Gnava.Core.Events.TranslationData;
import Gnava.Desktop.Facades.Translation;
import Gnava.Desktop.Interface.Elements.GnavaButton;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Options.FilterOptions;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Options.FilterOptionsManager;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Renderers.GameEventListRenderer;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.Translator;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.Map;

@Component
public class GameEventsPanel extends JPanel {
    private final JFrame parent;

    private final DefaultListModel<ExecutedGameEvent> eventListModel = new DefaultListModel<>();
    private final JList<ExecutedGameEvent> eventList = new JList<>(eventListModel);
    private final java.util.List<ExecutedGameEvent> allEvents = new java.util.ArrayList<>();

    private final Translator translator;
    private final FilterOptionsManager filterOptionsManager;

    public GameEventsPanel(
        GameEventListRenderer gameEventListRenderer,
        Translator translator,
        FilterOptionsManager filterOptionsManager
    ) {
        super(new BorderLayout(5, 5));
        this.translator = translator;
        this.filterOptionsManager = filterOptionsManager;
        this.parent = (JFrame) SwingUtilities.getWindowAncestor(this);

        GnavaButton filterButton = new GnavaButton("Filter");
        filterButton.addActionListener(l -> {
            FilterOptions options = filterOptionsManager.filterOptions();
            boolean nextState = !options.showOnlyMajorEvents();
            filterOptionsManager.setShowMajorEventsOnly(nextState);

            new PlaintextPopup(
                SwingUtilities.getWindowAncestor(this),
                "Showing only major events: %b".formatted(nextState)
            ).show();

            applyFilter(filterOptionsManager);
        });

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
        allEvents.addFirst(event);
        applyFilter(this.filterOptionsManager);
    }

    public void clear() {
        allEvents.clear();
        eventListModel.clear();
    }

    private void onEventSelected(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            ExecutedGameEvent selected = eventList.getSelectedValue();
            if (selected != null) {
                TranslationData translationData = selected.translationData();
                new PlaintextPopup(
                    parent,
                    translator.t(translationData.descriptionKey(), translationData.context()),
                    translator.t(
                        "ui.popups.event_details.title",
                        Map.of("day", String.valueOf(selected.happenedOnDay()))
                    )
                ).show();
                eventList.clearSelection();
            }
        }
    }

    private void applyFilter(FilterOptionsManager filterOptionsManager) {
        eventListModel.clear();

        FilterOptions options = filterOptionsManager.filterOptions();

        for (ExecutedGameEvent event : allEvents) {
            if (options.showOnlyMajorEvents() && event.isMinor()) {
                continue;
            }
            eventListModel.addElement(event);
        }
    }
}
