package Gnava.Core.EventBus.Events;

import Gnava.Core.Models.Settlement.Settlement;

public record SettlementCreatedEvent(Settlement newSettlement) { }
