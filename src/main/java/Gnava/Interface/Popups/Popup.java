package Gnava.Interface.Popups;

import Gnava.Interface.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: What if you enable default actions and then override button rendering?

public abstract class Popup {
    protected final JDialog dialog;
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private boolean withDefaultActions = false;

    private Runnable okAction;
    private Runnable cancelAction;

    protected Popup(String title, Dimension size) {
        dialog = new JDialog(GameFrame.getInstance(), title, true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(size);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(GameFrame.getInstance());

        dialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    public final void show() {
        registerKeyBindings();
        dialog.add(buildContent(), BorderLayout.CENTER);
        buttonPanel.removeAll();
        for (JButton btn : buildButtons()) {
            buttonPanel.add(btn);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
        dialog.setVisible(true);
    }

    protected abstract JComponent buildContent();

    protected final void close() {
        dialog.dispose();
    }

    protected void enableDefaultActions(Runnable actionOk, Runnable actionCancel) {
        withDefaultActions = true;
        okAction = actionOk;
        cancelAction = actionCancel;
    }

    protected JButton[] buildButtons() {
        if (withDefaultActions) {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                if (okAction != null) {
                    okAction.run();
                } else {
                    onOk();
                }
            });
            buttonPanel.add(okButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> {
                if (cancelAction != null){
                    cancelAction.run();
                } else {
                    onCancel();
                }
            });
            buttonPanel.add(cancelButton);

            return new JButton[] {okButton, cancelButton};
        }

        return new JButton[0];
    }

    // Default action
    protected void onOk() {
        close();
    }

    // Default action
    protected void onCancel() {
        close();
    }

    private void registerKeyBindings() {
        JRootPane root = dialog.getRootPane();
        InputMap inputMap = root.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = root.getActionMap();

        if (withDefaultActions) {
            inputMap.put(KeyStroke.getKeyStroke("ENTER"), "ok");
            actionMap.put("ok", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (okAction != null) {
                        okAction.run();
                    } else {
                        onOk();
                    }
                }
            });

            inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "cancel");
            actionMap.put("cancel", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cancelAction != null) {
                        cancelAction.run();
                    } else {
                        onCancel();
                    }
                }
            });
        }
    }
}
