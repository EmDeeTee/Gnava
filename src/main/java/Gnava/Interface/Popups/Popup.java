package Gnava.Interface.Popups;

import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Buttons.ButtonCancel;
import Gnava.Interface.Popups.Buttons.ButtonOk;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Popup<T> {
    protected final JDialog dialog;
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private boolean withDefaultOk = false;
    private boolean withDefaultCancel = false;
    private T result = null;

    private Runnable okAction;
    private Runnable cancelAction;
    List<JButton> buttonBuffer = new ArrayList<>();

    protected Popup(String title, Dimension size) {
        dialog = new JDialog(GameFrame.getInstance(), title, true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setSize(size);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(GameFrame.getInstance());

        dialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    public final T show() {
        dialog.add(buildContent(), BorderLayout.CENTER);
        buttonPanel.removeAll();
        for (JButton btn : buildButtons()) {
            buttonPanel.add(btn);
        }
        buttonPanel.revalidate();
        buttonPanel.repaint();
        registerKeyBindings();
        dialog.setVisible(true);

        return result;
    }

    protected abstract JComponent buildContent();

    protected final void close() {
        dialog.dispose();
    }

    protected void withDefaultOk(@Nullable Runnable actionOk) {
        withDefaultOk = true;
        okAction = actionOk;
    }

    protected void withDefaultCancel(@Nullable Runnable actionCancel) {
        withDefaultCancel = true;
        cancelAction = actionCancel;
    }

    protected JButton[] buildButtons() {
        if (withDefaultOk) {
            ButtonOk okButton = new ButtonOk();
            okButton.addActionListener(e -> {
                if (okAction != null) {
                    okAction.run();
                } else {
                    onOk();
                }
            });

            buttonBuffer.add(okButton);
        }

        if (withDefaultCancel) {
            ButtonCancel cancelButton = new ButtonCancel();
            cancelButton.addActionListener(e -> {
                if (cancelAction != null) {
                    cancelAction.run();
                } else {
                    onCancel();
                }
            });

            buttonBuffer.add(cancelButton);
        }

        return buttonBuffer.toArray(new JButton[0]);
    }

    // Default action
    protected void onOk() {
        close();
    }

    // Default action
    protected void onCancel() {
        close();
    }

    protected void setResult(T object) {
        result = object;
    }

    private void registerKeyBindings() {
        JRootPane root = dialog.getRootPane();
        InputMap inputMap = root.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = root.getActionMap();

        if (buttonBuffer.stream().anyMatch(btn -> btn instanceof ButtonOk)) {
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

        }

        if (buttonBuffer.stream().anyMatch(btn -> btn instanceof ButtonCancel)) {
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
