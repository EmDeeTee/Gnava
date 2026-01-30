package Gnava;

import Gnava.Game.Commands.CreateSettlementCommand;
import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Interface.Translations.TranslationKey;
import Gnava.Interface.Translations.TranslationManager;

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
