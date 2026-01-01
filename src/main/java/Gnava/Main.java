package Gnava;

import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Interface.Popups.Presets.PlaintextPopup;
import Gnava.Interface.Translations.TranslationKey;

public class Main {
    public static void main(String[] args) {
        // TODO: Maybe introduce GameFrame::onGameStart()?

        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(true);

        new PlaintextPopup(GameState.getInstance().getTranslationTable().t(TranslationKey.WELCOME_MESSAGE)).show();
        new CreateSettlementPopup(true, true).show().ifPresentOrElse(
                s -> GameState.getInstance().getSettlementManager().tryCreateSettlement(s),
                () -> { throw new IllegalStateException("Settlement creation cancelled"); }
        );
    }
}