package Gnava.Game;

import Gnava.Game.Events.Simulation.GameEvent;
import Gnava.Game.Managers.GameEventsManager;
import Gnava.Game.Managers.SettlementsManager;
import Gnava.Game.Managers.TimeManager;
import Gnava.Game.Settlements.Settlement;
import Gnava.Interface.Translations.TranslationTable;
import Gnava.Interface.Translations.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.TranslationTableEnglish;

import java.util.function.Consumer;

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

    // SETTLEMENT MANAGER
    // TODO: It can fail, but you don't know why
    public boolean tryCreateSettlement(Settlement settlement) {
        if (!canCreateSettlement(settlement)) {
            return false;
        }

        settlementManager.createSettlement(settlement);
        return true;
    }

    private boolean canCreateSettlement(Settlement settlement) {
        return true;
    }

    public Settlement[] getSettlements() {
        return settlementManager.getSettlements();
    }

    public Settlement getRandomSettlement() {
        return settlementManager.getRandomSettlement();
    }

    public void addSettlementCreatedListener(Consumer<Settlement> listener) {
        settlementManager.getDispatcher().addListener(listener);
    }

    public Settlement getPlayerSettlement() {
        return settlementManager.getPlayerSettlement();
    }

    // TIME MANAGER
    public void advanceTime() {
        timeManager.advanceTime();
    }

    public EventDispatcher<Integer> getTimeDispatcher() {
        return timeManager.getDispatcher();
    }

    // GAME EVENTS MANAGER
    public EventDispatcher<GameEvent> getGameEventDispatcher() {
        return gameEventsManager.getDispatcher();
    }

    public void generateGameEvent() {
        gameEventsManager.generate();
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }
}
