package Gnava.Interface.Popups;

import Gnava.Interface.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PopupBuilder {
    private final JDialog dialog;
    private JPanel contentPanel = new JPanel(new BorderLayout());
    private boolean withDefaultActions = false;
    private Consumer<JButton> okAction = null;
    private Consumer<JButton> cancelAction = null;

    public PopupBuilder(String title, int width, int height) {
        dialog = new JDialog(GameFrame.getInstance(), title, true);
        dialog.setSize(width, height);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);
    }

    public PopupBuilder withContent(JPanel panel) {
        this.contentPanel = panel;
        return this;
    }

    public PopupBuilder withDefaultActions() {
        this.withDefaultActions = true;
        return this;
    }

    public PopupBuilder onOk(Consumer<JButton> action) {
        this.okAction = action;
        return this;
    }

    public PopupBuilder onCancel(Consumer<JButton> action) {
        this.cancelAction = action;
        return this;
    }

    public void show() {
        dialog.add(contentPanel, BorderLayout.CENTER);

        if (withDefaultActions) {
            JPanel buttonPanel = new JPanel();
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");

            okButton.addActionListener(e -> {
                if (okAction != null) {
                    okAction.accept(okButton);
                }
            });

            cancelButton.addActionListener(e -> {
                if (cancelAction != null) {
                    cancelAction.accept(cancelButton);
                } else {
                    dialog.dispose();
                }
            });

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
        }

        dialog.setVisible(true);
    }

    public JDialog getDialog() {
        return dialog;
    }
}
