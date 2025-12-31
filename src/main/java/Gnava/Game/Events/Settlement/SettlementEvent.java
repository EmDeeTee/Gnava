package Gnava.Game.Events.Settlement;

import Gnava.Game.Settlements.Settlement;

// TODO: Maybe merge it into normal game events
public record SettlementEvent(Settlement settlement, EventType eventType) {
    public enum EventType { CREATED, REMOVED }
}
