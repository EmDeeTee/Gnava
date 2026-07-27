package Gnava.Core.EventBus.Events;

import Gnava.Core.GameEvents.Enums.GameOutcome;

public record GameOutcomeReceivedEvent(GameOutcome gameOutcome) { }
