package Gnava.Desktop.Interface.Popups;

import Gnava.Desktop.Interface.Elements.GnavaButton;
import Gnava.Desktop.Interface.Popups.Buttons.ButtonCancel;
import Gnava.Desktop.Interface.Popups.Buttons.ButtonOk;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: This code is a little crusty and could use a refactor
public abstract class Popup<T> {
    protected static final Dimension DEFAULT_DIMENSION = new Dimension(320, 205);

    protected final JDialog dialog;
    private final Window owner;
    private final Dimension size;
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private boolean withDefaultOk = false;
    private boolean withDefaultCancel = false;
    private T result = null;

    private Runnable okAction;
    private Runnable cancelAction;
    List<GnavaButton> buttonBuffer = new ArrayList<>();

    protected Popup(Window owner) {
        this(owner, "Popup", DEFAULT_DIMENSION);
    }

    protected Popup(Window owner, String title) {
        this(owner, title, DEFAULT_DIMENSION);
    }

    protected Popup(Window owner, String title, Dimension size) {
        this.owner = owner;
        this.size = size;

        dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLayout(new BorderLayout());

        dialog.add(buttonPanel, BorderLayout.SOUTH);
    }

    public final Optional<T> show() {
        dialog.add(buildContent(), BorderLayout.CENTER);
        buttonPanel.removeAll();
        for (GnavaButton btn : buildButtons()) {
            buttonPanel.add(btn);
        }
        registerKeyBindings();

        // A dialog that is realized at a fixed setSize() without ever being packed loses its
        // initial paint on X11/Wayland peers and comes up blank; Windows peers repaint anyway,
        // which is why this only broke on Linux. Pack first, then re-apply the fixed size.
        dialog.pack();
        dialog.setSize(size);
        // Centering has to happen after sizing, otherwise the dialog is placed using its
        // pre-layout dimensions and ends up off-centre.
        dialog.setLocationRelativeTo(owner);

        dialog.setVisible(true);

        return Optional.ofNullable(result);
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

    protected GnavaButton[] buildButtons() {
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

        return buttonBuffer.toArray(new GnavaButton[0]);
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
