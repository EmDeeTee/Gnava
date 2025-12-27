package Gnava.Game.Events.Settlement;

import Gnava.Game.Settlements.Settlement;

public record SettlementEvent(Settlement settlement, EventType eventType) {
    public enum EventType { CREATED, REMOVED }
}
