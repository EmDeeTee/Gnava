package Gnava.Repositories;

import Gnava.Core.Events.IGameEvent;
import Gnava.Core.Repositories.IGameEventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEventRepository implements IGameEventRepository {
    private final List<IGameEvent> registeredGameEvents = new ArrayList<>();

    @Override
    public List<IGameEvent> getRegisteredGameEvents() {
        return Collections.unmodifiableList(registeredGameEvents);
    }
}
