package Gnava.Game;

import Gnava.Game.Managers.SettlementManager;
import Gnava.Game.Managers.TimeManager;
import Gnava.Game.Settlements.Settlement;
import Gnava.Interface.Translations.TranslationTable;
import Gnava.Interface.Translations.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.TranslationTableEnglish;

import java.util.function.Consumer;

public class GameState {
    private static GameState instance = null;
    private final SettlementManager settlementManager = new SettlementManager();
    private final TimeManager timeManager = new TimeManager();
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

    // SETTLEMENT MANAGER
    // TODO: It can fail, but you don't know why
    public boolean tryCreateSettlement(Settlement settlement) {
        if (!canCreateSettlement(settlement)) {
            return false;
        }

        settlementManager.createSettlement(settlement);
        return true;
    }

    public Settlement[] getSettlements() {
        return settlementManager.getSettlements();
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        settlementManager.getDispatcher().addListener(listener);
    }

    // TIME MANAGER
    public void advanceTime() {
        timeManager.advanceTime();
    }

    public EventDispatcher<Integer> getTimeDispatcher() {
        return timeManager.getDispatcher();
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }

    private boolean canCreateSettlement(Settlement settlement) {
        return true;
    }
}
