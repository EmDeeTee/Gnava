package Gnava.Desktop.Interface.Frames.ChartFrame;

import Gnava.Desktop.Interface.Translations.Translator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Component
public final class ChartFrame extends JFrame {
    private final Translator translator;

    public ChartFrame(Translator translator) {
        this.translator = translator;
    }

    public void display() {
        setPreferredSize(new Dimension(800, 600));
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
        setResizable(false);

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("test", 100);

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
    }
}
