package Gnava.Game.Managers;

import Gnava.Game.Events.EventContext;
import Gnava.Game.Events.GameEventDefinition;

import java.util.List;

public record EventCandidates(List<GameEventDefinition> candidates, double totalWeight, EventContext context) { }
