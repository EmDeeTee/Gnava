package Gnava;

import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Popups.Presets.CreateSettlementPopup;
import Gnava.Interface.Popups.Presets.PlaintextPopup;

public class Main {
    public static void main(String[] args) {
        // TODO: Maybe introduce GameFrame::onGameStart()?

        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(true);

        new PlaintextPopup("Welcome, to the Kingdoms of Gnava. You are a god-like being overseeing this world. <br><br> Create your settlement and help it become the most powerful and prosperous land in the realm.").show();
        new CreateSettlementPopup(true).show().ifPresentOrElse(
                s -> GameState.getInstance().tryCreateSettlement(s),
                () -> { throw new IllegalStateException("Settlement creation cancelled"); }
        );
    }
}