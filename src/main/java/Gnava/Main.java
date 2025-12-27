package Gnava;

import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

public class Main {
    public static void main(String[] args) {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(true);
        new CreateSettlementPopup(true).show().ifPresent(s -> GameState.getInstance().tryCreateSettlement(s));
    }
}