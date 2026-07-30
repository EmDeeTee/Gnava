package Gnava.Core.GameEvents.Contexts;

import Gnava.Core.Statistics.Records.WorldStatistics;
import Gnava.Core.TimeState;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import Gnava.ModApi.GameEvents.IModEventContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class EventContext implements IModEventContext {
    private final TimeState timeState;
    private final Map<String, Object> attachments = new HashMap<>();
    private final WorldStatisticsProvider worldStatisticsProvider;

    public EventContext(
            TimeState timeState,
            WorldStatisticsProvider worldStatisticsProvider
    ) {
        this.timeState = timeState;
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

    public TimeState getGameState() {
        return timeState;
    }

    public WorldStatistics getWorldStatistics() {
        return worldStatisticsProvider.getWorldStatistics();
    }
}
