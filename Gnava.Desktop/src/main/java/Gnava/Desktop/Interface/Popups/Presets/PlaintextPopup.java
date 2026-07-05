package Gnava.Desktop.Interface.Popups.Presets;

import Gnava.Desktop.Interface.Popups.Popup;

import javax.swing.*;
import java.awt.*;

public class PlaintextPopup extends Popup<Boolean> {
    private final String message;

    public PlaintextPopup(Window owner, String message) {
        super(owner);
        this.message = "<html><div style='width:200px;'>" + message + "</div></html>";
        withDefaultOk(null);
    }

    @Override
    protected JComponent buildContent() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(label);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(DEFAULT_DIMENSION);
        return scrollPane;
    }
}
