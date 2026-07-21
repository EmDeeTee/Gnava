package Gnava.Desktop.Interface.Frames.DetailsFrame;

import Gnava.Core.EventBus.Events.SettlementCreatedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Desktop.Interface.Translations.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public final class DetailsFrame extends JFrame {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailsFrame.class);
    private static final Dimension PREFERRED_SIZE = new Dimension(600, 400);
    private final SettlementTableModel settlementTableModel;
    private final Translator translator;

    public DetailsFrame(ISettlementProvider settlementProvider, Translator translator) {
        settlementTableModel = new SettlementTableModel(settlementProvider.getAll());
        this.translator = translator;
    }

    public void display() {
        setTitle(translator.t("ui.frames.details"));
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setResizable(false);

        JTable table = new JTable(settlementTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        pack();
    }

    @EventListener
    private void onSettlementAdded(SettlementCreatedEvent event) {
        if (isVisible()) {
            LOGGER.debug("Updating table because settlement got added");
            settlementTableModel.fireTableDataChanged();
        }
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        if (isVisible()) {
            LOGGER.debug("Updating table because time advanced");
            settlementTableModel.fireTableDataChanged();
        }
    }
}
