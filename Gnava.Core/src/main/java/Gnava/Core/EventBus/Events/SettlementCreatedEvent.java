package Gnava.Core.EventBus.Events;

import Gnava.Core.Settlements.Settlement;

public record SettlementCreatedEvent(Settlement newSettlement) { }
