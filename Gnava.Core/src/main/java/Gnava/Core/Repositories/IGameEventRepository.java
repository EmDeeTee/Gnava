package Gnava.Core.Repositories;

import Gnava.Core.Events.IGameEvent;

import java.util.List;

public interface IGameEventRepository {
    List<IGameEvent> getRegisteredGameEvents();
}
