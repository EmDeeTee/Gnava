package Gnava.Desktop.Interface.Popups.Presets;

import Gnava.Core.Managers.Settlement.SettlementCreationPolicy;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Settlements.Enums.SettlementWealthLevel;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;
import Gnava.Core.Settlements.Enums.SettlementPopulationType;
import Gnava.Desktop.Interface.Elements.GnavaButton;
import Gnava.Desktop.Interface.Popups.Popup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;

import javax.swing.*;
import java.awt.*;

public final class CreateSettlementPopup extends Popup<CreateSettlementRequest> {
    private final JTextField nameField = new JTextField(15);
    private final JComboBox<SettlementPopulationType> populationTypeCombo = new JComboBox<>(SettlementPopulationType.values());
    private final boolean player;
    private final SettlementNameGenerator settlementNameGenerator;
    private final SettlementCreationPolicy settlementCreationPolicy;

    public CreateSettlementPopup(Window mainFrame, SettlementNameGenerator settlementNameGenerator, SettlementCreationPolicy settlementCreationPolicy) {
        this(
            mainFrame,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT),
            false,
            false,
            settlementNameGenerator,
            settlementCreationPolicy
        );
    }

    public CreateSettlementPopup(
        Window mainFrame,
        String title,
        boolean forced,
        boolean isForPlayer,
        SettlementNameGenerator settlementNameGenerator,
        SettlementCreationPolicy settlementCreationPolicy
    ) {
        super(mainFrame, title);
        this.settlementNameGenerator = settlementNameGenerator;
        this.settlementCreationPolicy = settlementCreationPolicy;
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
        panel.add(new JLabel(TranslationManager.getInstance().getTranslationTable().t(TranslationKey.POPULATION_TYPE)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        panel.add(populationTypeCombo, gbc);

        return panel;
    }

    private void submit() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                dialog,
                TranslationManager.getInstance().getTranslationTable().t(TranslationKey.ERROR_NAME_CANT_BE_EMPTY),
                TranslationManager.getInstance().getTranslationTable().t(TranslationKey.ERROR),
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        CreateSettlementRequest request = new CreateSettlementRequest(
            name,
            1,
            10,
            (SettlementPopulationType) populationTypeCombo.getSelectedItem(),
            SettlementWealthLevel.MODERATE,
            player
        );
        SettlementCreationResult result = settlementCreationPolicy.validate(request);
        if (result.ok()) {
            setResult(request);
            close();
        } else {
            JOptionPane.showMessageDialog(
                JOptionPane.getRootFrame(),
                result.reason()
            );
            return;
        }
    }
}
