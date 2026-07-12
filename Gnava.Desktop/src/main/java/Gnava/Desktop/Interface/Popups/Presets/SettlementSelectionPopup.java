package Gnava.Desktop.Interface.Popups.Presets;

import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Desktop.Interface.Popups.Popup;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class SettlementSelectionPopup extends Popup<Settlement> {
    private final JComboBox<Settlement> settlementBox;

    public SettlementSelectionPopup(Window owner, List<Settlement> settlements) {
        super(owner, "Pick a settlement", new Dimension(320, 125));
        this.settlementBox = new JComboBox<>(
            settlements.toArray(new Settlement[0])
        );
        withDefaultOk(this::submit);
    }

    @Override
    protected JComponent buildContent() {
        JPanel panel = new JPanel(new GridLayout(1, 1, 1, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(settlementBox);

        return panel;
    }

    private void submit() {
        setResult((Settlement) settlementBox.getSelectedItem());
        close();
    }
}
