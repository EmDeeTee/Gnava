package Gnava.Game;

import Gnava.Game.Managers.SettlementManager;
import Gnava.Game.Settlements.Settlement;
import Gnava.Interface.Translations.TranslationTable;
import Gnava.Interface.Translations.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.TranslationTableEnglish;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameState {
    private static GameState instance = null;
    private final SettlementManager settlementManager = new SettlementManager();
    private final TranslationTable translationTable = System.getProperty("os.name").startsWith("Windows")
        ? new TranslationTableEnglish()
        : new TranslationTableCrustyDutch();

    private Integer currentDay = 0;

    private final List<Consumer<Integer>> timeListeners = new ArrayList<>();

    private GameState() { }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

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
        settlementManager.addSettlementCreatedListener(listener);
    }

    public void advanceTime() {
        currentDay++;
        notifyTimeListeners();
    }

    public void addTimeListener(Consumer<Integer> listener) {
        timeListeners.add(listener);
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }

    private void notifyTimeListeners() {
        for (Consumer<Integer> listener : timeListeners) {
            listener.accept(currentDay);
        }
    }

    private boolean canCreateSettlement(Settlement settlement) {
        return true;
    }
}
