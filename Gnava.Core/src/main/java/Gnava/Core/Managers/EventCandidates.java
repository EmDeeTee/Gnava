package Gnava.Core.Managers;

import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Events.IGameEventDefinition;

import java.util.List;

public record EventCandidates<T extends EventContext>(
    List<IGameEventDefinition<T>> candidates,
    double totalWeight,
    T context
) { }
