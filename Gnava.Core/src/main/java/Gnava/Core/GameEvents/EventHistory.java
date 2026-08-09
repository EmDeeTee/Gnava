package Gnava.Core.GameEvents;

import Gnava.GameApi.GameEvents.GameEventId;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public final class EventHistory {
    private final Set<GameEventId> eventIds = new HashSet<>();

    public synchronized boolean contains(GameEventId eventId) {
        return eventIds.contains(eventId);
    }

    public synchronized void record(GameEventId eventId) {
        eventIds.add(eventId);
    }
}
