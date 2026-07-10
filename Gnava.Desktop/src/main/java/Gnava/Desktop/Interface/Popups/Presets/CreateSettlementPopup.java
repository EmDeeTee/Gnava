package Gnava.Desktop.Interface.Popups.Presets;

import Gnava.Core.Models.Settlement.Enums.SettlementWealthLevel;
import Gnava.Core.Models.Settlement.Settlement;
import Gnava.Core.Models.Settlement.Enums.SettlementPopulationType;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Popup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;

import javax.swing.*;
import java.awt.*;

public final class CreateSettlementPopup extends Popup<Settlement> {
    private final JTextField nameField = new JTextField(15);
    private final JComboBox<SettlementPopulationType> populationTypeCombo = new JComboBox<>(SettlementPopulationType.values());
    private final boolean player;

    public CreateSettlementPopup(MainFrame mainFrame) {
        this(
            mainFrame,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT),
            false,
            false
        );
    }

    public CreateSettlementPopup(
        MainFrame mainFrame,
        String title,
        boolean forced,
        boolean isForPlayer
    ) {
        super(mainFrame, title);
        withDefaultOk(this::submit);
        if (!forced) {
            withDefaultCancel(null);
        }
        player = isForPlayer;
    }

    @Override
    protected JComponent buildContent() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel(TranslationManager.getInstance().getTranslationTable().t(TranslationKey.NAME) + ":"));
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

        setResult(new Settlement(
            name,
            1,
            10,
            (SettlementPopulationType) populationTypeCombo.getSelectedItem(),
            SettlementWealthLevel.MODERATE,
            player
        ));

        close();
    }
}
