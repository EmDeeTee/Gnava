package Gnava.Desktop;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.Managers.Settlement.SettlementCreationPolicy;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Desktop.Interface.Actions.CreateSettlementAction;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import org.springframework.stereotype.Component;

@Component
public class Gnava {
    private final MainFrame mainFrame;
    private final SettlementManager settlementManager;
    private final SettlementNameGenerator settlementNameGenerator;
    private final SettlementCreationPolicy settlementCreationPolicy;

    public Gnava(
        MainFrame mainFrame,
        SettlementManager settlementManager,
        SettlementNameGenerator settlementNameGenerator,
        SettlementCreationPolicy settlementCreationPolicy
    ) {
        this.mainFrame = mainFrame;
        this.settlementManager = settlementManager;
        this.settlementNameGenerator = settlementNameGenerator;
        this.settlementCreationPolicy = settlementCreationPolicy;
    }

    public void initUi() {
        CreateSettlementCommand createSettlementCommand = new CreateSettlementCommand(settlementManager);

        mainFrame.setVisible(true);

        new PlaintextPopup(mainFrame, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.WELCOME_MESSAGE)).show();

        new CreateSettlementAction(
            createSettlementCommand,
            () -> new CreateSettlementPopup(
                mainFrame,
                TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT),
                true,
                true,
                settlementNameGenerator,
                settlementCreationPolicy
            ).show().orElseThrow()
        ).execute();
    }
}
