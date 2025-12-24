package Gnava.Interface.Popups;

import Gnava.Game.GameState;
import Gnava.Game.Settlements.Settlement;
import Gnava.Game.Settlements.SettlementPopulationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CreateSettlementPopup implements IPopup {
    JTextField nameField = new JTextField(2);
    JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
    JComboBox<SettlementPopulationType> populationTypeCombo = new JComboBox<>(SettlementPopulationType.values());

    public void show() {
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Population type:"));
        inputPanel.add(populationTypeCombo);

        nameField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter pressed!");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        new PopupBuilder("Create Settlement", 300, 200)
                .withContent(inputPanel)
                .withDefaultActions()
                .onOk(this::onOk)
                .show();
    }

    private void onOk(JButton btn) {
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
    }
}
