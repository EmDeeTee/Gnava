package Gnava.Core.EventBus.Events;

import Gnava.Core.Events.Enums.GameOutcome;

public record GameOutcomeReceivedEvent(GameOutcome gameOutcome) { }
