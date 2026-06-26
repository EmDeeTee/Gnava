package Gnava.Core.Events;

import Gnava.Core.Models.Settlement;

// TODO: Maybe merge it into normal game events
public record SettlementEvent(Settlement settlement, EventType eventType) {
    public enum EventType { CREATED, REMOVED }
}
