package Gnava.Repositories;

import Gnava.Core.GameEvents.IGameEventDefinition;
import Gnava.Core.Repositories.IGameEventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEventRepository implements IGameEventRepository {
    private final List<IGameEventDefinition> registeredGameEvents = new ArrayList<>();

    @Override
    public List<IGameEventDefinition> getRegisteredGameEvents() {
        return Collections.unmodifiableList(registeredGameEvents);
    }
}
