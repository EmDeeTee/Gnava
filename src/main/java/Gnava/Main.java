package Gnava;

import Gnava.Game.GameState;
import Gnava.Interface.GameFrame;
import Gnava.Interface.Translations.TranslationKey;
import Gnava.Interface.Translations.TranslationManager;

public class Main {
    public static void main(String[] args) {
        GameState gameState = new GameState();
        GameFrame gameFrame = new GameFrame(gameState, TranslationManager.getInstance().getTranslationTable().t(TranslationKey.GKINGDOMS));

        Gnava gnava = new Gnava(gameState, gameFrame);
        gnava.initUi();
    }
}