package Gnava.Desktop.Interface.Popups.Presets;

import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Desktop.Interface.Elements.GnavaButton;
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
    private final SettlementNameGenerator settlementNameGenerator;

    public CreateSettlementPopup(MainFrame mainFrame, SettlementNameGenerator settlementNameGenerator) {
        this(
            mainFrame,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT),
            false,
            false, settlementNameGenerator
        );
    }

    public CreateSettlementPopup(
        MainFrame mainFrame,
        String title,
        boolean forced,
        boolean isForPlayer,
        SettlementNameGenerator settlementNameGenerator
    ) {
        super(mainFrame, title);
        this.settlementNameGenerator = settlementNameGenerator;
        withDefaultOk(this::submit);
        if (!forced) {
            withDefaultCancel(null);
        }
        player = isForPlayer;
    }

    @Override
    protected JComponent buildContent() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        panel.add(new JLabel(TranslationManager.getInstance().getTranslationTable().t(TranslationKey.NAME) + ":"), gbc);

        JPanel nameFieldPane = new JPanel();
        nameFieldPane.setLayout(new BoxLayout(nameFieldPane, BoxLayout.X_AXIS));
        nameFieldPane.add(nameField);
        nameFieldPane.add(Box.createHorizontalStrut(5));
        GnavaButton randomNameButton = new GnavaButton("Random");
        randomNameButton.addActionListener(l -> {
            SettlementPopulationType populationType = (SettlementPopulationType) populationTypeCombo.getSelectedItem();
            String name = settlementNameGenerator.generate(populationType).name();
            nameField.setText(name);
        });
        nameFieldPane.add(randomNameButton);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel.add(nameFieldPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Population type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(populationTypeCombo, gbc);

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
