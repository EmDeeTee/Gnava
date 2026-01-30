package Gnava.Game.Events;

import Gnava.Game.Models.Settlement;

// TODO: Maybe merge it into normal game events
public record SettlementEvent(Settlement settlement, EventType eventType) {
    public enum EventType { CREATED, REMOVED }
}
