package Gnava.Core.Managers;

import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.Core.GameEvents.IGameEventDefinition;

import java.util.List;

public record EventCandidates<T extends EventContext>(
    List<IGameEventDefinition<T>> candidates,
    double totalWeight,
    T context
) { }
