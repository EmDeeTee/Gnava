package Gnava.Interface.Popups.Presets;

import Gnava.Game.Settlements.Settlement;
import Gnava.Game.Settlements.SettlementPopulationType;
import Gnava.Interface.Popups.Popup;

import javax.swing.*;
import java.awt.*;

public final class CreateSettlementPopup extends Popup<Settlement> {
    private final JTextField nameField = new JTextField(15);
    private final JComboBox<SettlementPopulationType> populationTypeCombo = new JComboBox<>(SettlementPopulationType.values());

    public CreateSettlementPopup() {
        this(false);
    }

    public CreateSettlementPopup(boolean forced) {
        super("Create Settlement", new Dimension(300, 200));
        withDefaultOk(this::submit);
        if (!forced) {
            withDefaultCancel(null);
        }
    }

    @Override
    protected JComponent buildContent() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Population type:"));
        panel.add(populationTypeCombo);

        return panel;
    }

    private void submit() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        setResult(
            new Settlement(
                name,
                1,
                10,
                (SettlementPopulationType) populationTypeCombo.getSelectedItem()
            )
        );

        close();
    }
}
