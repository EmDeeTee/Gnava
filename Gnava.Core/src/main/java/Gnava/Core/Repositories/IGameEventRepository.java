package Gnava.Core.Repositories;

import Gnava.Core.GameEvents.IGameEventDefinition;

import java.util.List;

public interface IGameEventRepository {
    List<IGameEventDefinition> getRegisteredGameEvents();
}
