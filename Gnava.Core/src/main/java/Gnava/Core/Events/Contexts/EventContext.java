package Gnava.Core.Events.Contexts;

import Gnava.Core.GameState;
import Gnava.Core.Managers.SettlementManager;
import Gnava.Core.Statistics.WorldStatisticsProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO: Maybe have specific event contexts? Like SettlementEventContext etc
public final class EventContext {
    private final Object subject;
    private final GameState gameState;
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final SettlementManager settlementManager;
    private final Map<String, Object> attachments = new HashMap<>();

    public EventContext(
        Object subject,
        GameState gameState,
        WorldStatisticsProvider worldStatisticsProvider,
        SettlementManager settlementManager
    ) {
        this.subject = subject;
        this.gameState = gameState;
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.settlementManager = settlementManager;
    }

    public <T> void set(String id, T object) {
        attachments.put(id, object);
    }

    public <T> Optional<T> get(String id, Class<T> type) {
        Object object = attachments.get(id);

        if (type.isInstance(object)) {
            return Optional.of(type.cast(object));
        }

        return Optional.empty();
    }

    public Object getSubject() {
        return subject;
    }

    public GameState getGameState() {
        return gameState;
    }

    public WorldStatisticsProvider getWorldStatisticsProvider() {
        return worldStatisticsProvider;
    }

    public SettlementManager getSettlementManager() {
        return settlementManager;
    }
}
