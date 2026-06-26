package Gnava.Desktop;

import Gnava.Core.Commands.CreateSettlementCommand;
import Gnava.Core.GameState;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Desktop.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;

public class Gnava {
    private final GameState gameState;
    private final GameFrame gameFrame;

    public Gnava(GameState gameState, GameFrame gameFrame) {
        this.gameState = gameState;
        this.gameFrame = gameFrame;
    }

    public void initUi() {
        CreateSettlementCommand command = new CreateSettlementCommand(gameState.getSettlementManager());

        gameFrame.setVisible(true);
        new PlaintextPopup(gameFrame, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.WELCOME_MESSAGE)).show();
        new CreateSettlementPopup(gameFrame, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CREATE_SETTLEMENT), true, true).show().ifPresentOrElse(
            s -> gameState.getSettlementManager().tryCreateSettlement(s),
            () -> { throw new IllegalStateException("Settlement creation cancelled"); }
        );
    }

    public GameState getGameState() {
        return gameState;
    }
}
