package Gnava.Game.Managers;

import Gnava.Game.Events.GameEvent;
import Gnava.Game.Events.EventContext;

import java.util.List;

public record EventCandidates(List<GameEvent> candidates, double totalWeight, EventContext context) { }
