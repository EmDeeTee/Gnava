package Gnava.Interface.Popups;

import Gnava.Interface.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class PopupBuilder {
    private final JDialog dialog;

    private JPanel contentPanel = new JPanel(new BorderLayout());
    private final JPanel buttonPanel = new JPanel();
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

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
            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Cancel");

            Action okActionDefault = createOkAction(okButton);
            Action cancelActionDefault = createCancelAction(cancelButton);

            okButton.setAction(okActionDefault);
            okButton.setText("OK");

            cancelButton.setAction(cancelActionDefault);
            cancelButton.setText("Cancel");

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            bindKeys(okActionDefault, cancelActionDefault);
        }

        dialog.setVisible(true);
    }

    public JDialog getDialog() {
        return dialog;
    }

    private Action createOkAction(JButton button) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (okAction != null) {
                    okAction.accept(button);
                }
            }
        };
    }

    private Action createCancelAction(JButton button) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cancelAction != null) {
                    cancelAction.accept(button);
                } else {
                    dialog.dispose();
                }
            }
        };
    }

    private void bindKeys(Action okAction, Action cancelAction) {
        JRootPane root = dialog.getRootPane();

        InputMap inputMap = root.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = root.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ok");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");

        actionMap.put("ok", okAction);
        actionMap.put("cancel", cancelAction);
    }
}
