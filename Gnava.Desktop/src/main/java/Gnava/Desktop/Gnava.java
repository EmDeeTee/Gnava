package Gnava.Desktop;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.Managers.Settlement.SettlementCreationPolicy;
import Gnava.Core.Settlements.NameGenerator.SettlementNameGenerator;
import Gnava.Core.Startup;
import Gnava.Desktop.Interface.Frames.MainFrame.MainFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.NoSuchElementException;

@Component
public class Gnava {
    private final MainFrame mainFrame;
    private final SettlementNameGenerator settlementNameGenerator;
    private final SettlementCreationPolicy settlementCreationPolicy;
    private final CreateSettlementCommand createSettlementCommand;
    private final Startup startup;

    public Gnava(
        MainFrame mainFrame,
        SettlementNameGenerator settlementNameGenerator,
        SettlementCreationPolicy settlementCreationPolicy,
        CreateSettlementCommand createSettlementCommand,
        Startup startup
    ) {
        this.mainFrame = mainFrame;
        this.settlementNameGenerator = settlementNameGenerator;
        this.settlementCreationPolicy = settlementCreationPolicy;
        this.createSettlementCommand = createSettlementCommand;
        this.startup = startup;
    }

    public void initUi() {
        try {
            startup.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainFrame.setVisible(true);

        new PlaintextPopup(
            mainFrame,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.WELCOME_MESSAGE)
        ).show();

        createSettlementCommand.execute(
            new CreateSettlementPopup(
                mainFrame,
                TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT),
                true,
                true,
                settlementNameGenerator,
                settlementCreationPolicy
            ).show().orElseThrow(() -> new NoSuchElementException("This popup should not have the ability to be closed/canceled. So if this crashes then gg"))
        );
    }
}
