package Gnava.Desktop;


import Gnava.Core.GameState;
import Gnava.Desktop.Interface.Frame.GameFrame;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import Gnava.Repositories.SettlementRepository;

public class Main {
    public static void main(String[] args) {
        SettlementRepository settlementRepository = new SettlementRepository();
        GameState gameState = new GameState(settlementRepository);
        GameFrame gameFrame = new GameFrame(
            gameState,
            TranslationManager.getInstance().getTranslationTable().t(TranslationKey.GKINGDOMS)
        );

        Gnava gnava = new Gnava(gameState, gameFrame);
        gnava.initUi();
    }
}