package Gnava.Interface.Popups;

import Gnava.Game.GameState;
import Gnava.Game.Settlements.Settlement;
import Gnava.Game.Settlements.SettlementPopulationType;

import javax.swing.*;
import java.awt.*;

public class CreateSettlementPopup implements IPopup {
    public void show() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField nameField = new JTextField(2);
        JComboBox<SettlementPopulationType> populationTypeCombo = new JComboBox<>(SettlementPopulationType.values());

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Population type:"));
        inputPanel.add(populationTypeCombo);

        new PopupBuilder("Create Settlement", 300, 200)
                .withContent(inputPanel)
                .withDefaultActions()
                .onOk(btn -> {
                    String name = nameField.getText().trim();

                    if (!name.isEmpty()) {
                        GameState.getInstance().addSettlement(new Settlement(
                            name,
                            1,
                            10,
                            (SettlementPopulationType)populationTypeCombo.getModel().getSelectedItem()
                        ));
                        SwingUtilities.getWindowAncestor(btn).dispose();
                    } else {
                        JOptionPane.showMessageDialog(btn, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                })
                .show();
    }
}
