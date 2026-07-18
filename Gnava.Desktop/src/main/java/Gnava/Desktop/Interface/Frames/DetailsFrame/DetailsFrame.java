package Gnava.Desktop.Interface.Frames.DetailsFrame;

import Gnava.Core.EventBus.Events.SettlementCreatedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Desktop.Interface.Translations.Translator;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public final class DetailsFrame extends JFrame {
    private static final Dimension PREFERRED_SIZE = new Dimension(600, 400);
    private final SettlementTableModel settlementTableModel;

    public DetailsFrame(ISettlementProvider settlementProvider, Translator translator) {
        setTitle(translator.t("ui.frames.details"));
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setSize(PREFERRED_SIZE);
        setMinimumSize(PREFERRED_SIZE);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        settlementTableModel = new SettlementTableModel(settlementProvider.getAll());
        JTable table = new JTable(settlementTableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        pack();
    }

    @EventListener
    private void onSettlementAdded(SettlementCreatedEvent event) {
        settlementTableModel.fireTableDataChanged();
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        settlementTableModel.fireTableDataChanged();
    }
}
