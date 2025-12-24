package Gnava;

import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;

public class Main {
    public static void main(String[] args) {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(true);
        CreateSettlementPopup popup = new CreateSettlementPopup();
        popup.show();
    }
}