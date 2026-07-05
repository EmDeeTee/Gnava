package Gnava.Desktop;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.GameState;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;

public class Gnava {
    private final GameState gameState;
    private final GameFrame gameFrame;
    private final SettlementManager settlementManager;

    public Gnava(
        GameState gameState,
        GameFrame gameFrame,
        SettlementManager settlementManager
    ) {
        this.gameState = gameState;
        this.gameFrame = gameFrame;
        this.settlementManager = settlementManager;
    }

    public void initUi() {
        CreateSettlementCommand command = new CreateSettlementCommand(settlementManager);

        gameFrame.setVisible(true);
        new PlaintextPopup(gameFrame, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.WELCOME_MESSAGE)).show();
        new CreateSettlementPopup(gameFrame, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT), true, true).show().ifPresentOrElse(
            settlementManager::tryCreateSettlement,
            () -> { throw new IllegalStateException("Settlement creation cancelled"); }
        );
    }

    public GameState getGameState() {
        return gameState;
    }
}
