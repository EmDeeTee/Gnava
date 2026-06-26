package Gnava.Core.Managers;

import Gnava.Core.Events.EventContext;
import Gnava.Core.Events.GameEventDefinition;

import java.util.List;

public record EventCandidates(List<GameEventDefinition> candidates, double totalWeight, EventContext context) { }
