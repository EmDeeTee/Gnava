package Gnava.Core.Events.Contexts;

import Gnava.Core.Dto.WorldStatistics;
import Gnava.Core.GameState;
import Gnava.Core.Statistics.WorldStatisticsProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class EventContext {
    private final GameState gameState;
    private final Map<String, Object> attachments = new HashMap<>();
    private final WorldStatisticsProvider worldStatisticsProvider;

    public EventContext(
            GameState gameState,
            WorldStatisticsProvider worldStatisticsProvider
    ) {
        this.gameState = gameState;
        this.worldStatisticsProvider = worldStatisticsProvider;
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

    public GameState getGameState() {
        return gameState;
    }

    public WorldStatistics getWorldStatistics() {
        return worldStatisticsProvider.getWorldStatistics();
    }
}
