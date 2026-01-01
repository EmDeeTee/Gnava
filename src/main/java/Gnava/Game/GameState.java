package Gnava.Game;

import Gnava.Game.DataTransferObjects.WorldStatistics;
import Gnava.Game.Managers.GameEventsManager;
import Gnava.Game.Managers.SettlementsManager;
import Gnava.Game.Managers.TimeManager;
import Gnava.Interface.Translations.TranslationTable;
import Gnava.Interface.Translations.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.TranslationTableEnglish;

// FIXME: REVOLUTION! Managers become public, and rules checking, like for adding a settlement will get moved to the respective manager
public class GameState {
    private static GameState instance = null;

    private final SettlementsManager settlementManager = new SettlementsManager();
    private final TimeManager timeManager = new TimeManager();
    private final GameEventsManager gameEventsManager = new GameEventsManager();

    private final TranslationTable translationTable = System.getProperty("os.name").startsWith("Windows")
        ? new TranslationTableEnglish()
        : new TranslationTableCrustyDutch();

    private GameState() { }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public WorldStatistics getWorldStatistics() {
        return new WorldStatistics(
            settlementManager.getWorldPopulation(),
            settlementManager.getSettlementCount()
        );
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public GameEventsManager getGameEventsManager() {
        return gameEventsManager;
    }

    public SettlementsManager getSettlementManager() {
        return settlementManager;
    }
}
