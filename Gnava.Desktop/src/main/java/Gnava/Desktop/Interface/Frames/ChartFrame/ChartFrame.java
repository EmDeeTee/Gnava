package Gnava.Desktop.Interface.Frames.ChartFrame;

import Gnava.Core.EventBus.Events.SettlementCreatedEvent;
import Gnava.Core.EventBus.Events.TimeAdvancedEvent;
import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.Core.Settlements.Settlement;
import Gnava.Desktop.Interface.Translations.Translator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// FIXME: Can't this memory leak?
@Component
public final class ChartFrame extends JFrame {
    private final Translator translator;
    private final ISettlementProvider settlementProvider;

    private final DefaultPieDataset<String> dataset;

    public ChartFrame(Translator translator, ISettlementProvider settlementProvider) {
        this.translator = translator;
        this.settlementProvider = settlementProvider;
        this.dataset = new DefaultPieDataset<>();
    }

    public void display() {
        setPreferredSize(new Dimension(800, 600));
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setResizable(false);

        JFreeChart chart = ChartFactory.createPieChart(
            translator.t("ui.frames.chart_title"),
            dataset,
            true,
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);

        pack();

        refreshData();
    }

    private void refreshData() {
        dataset.clear();
        for (Settlement settlement : settlementProvider.getAll()) {
            dataset.setValue(settlement.getName(), settlement.getTotalPopulation());
        }
    }

    @EventListener
    private void onTimeAdvanced(TimeAdvancedEvent event) {
        SwingUtilities.invokeLater(this::refreshData);
    }

    @EventListener
    private void onSettlementAdded(SettlementCreatedEvent event) {
        SwingUtilities.invokeLater(this::refreshData);
    }
}
