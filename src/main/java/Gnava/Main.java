package Gnava;

import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

public class Main {
    public static void main(String[] args) {
        // TODO: Maybe introduce GameFrame::onGameStart()?

        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(true);

        new CreateSettlementPopup(true).show().ifPresentOrElse(
                s -> GameState.getInstance().tryCreateSettlement(s),
                () -> { throw new IllegalStateException("Settlement creation cancelled"); }
        );
    }
}