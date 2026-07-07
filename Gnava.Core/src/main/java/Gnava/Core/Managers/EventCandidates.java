package Gnava.Core.Managers;

import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.IGameEvent;

import java.util.List;

public record EventCandidates(List<IGameEvent> candidates, double totalWeight, EventContext context) { }
