package Gnava.Core.Mod.Context;

import Gnava.Core.GameEvents.GameEventRegistrar;
import Gnava.GameApi.GameEvents.IGameEventRegistrar;
import Gnava.ModApi.IModContext;
import Gnava.GameApi.IGameTimeApi;
import Gnava.GameApi.ISettlementApi;
import org.springframework.stereotype.Service;

@Service
public class ModContext implements IModContext {
    private final SettlementApi settlementApi;
    private final GameTimeApi gameTimeApi;
    private final GameEventRegistrar gameEventRegistrar;

    public ModContext(
        SettlementApi settlementApi,
        GameTimeApi gameTimeApi,
        GameEventRegistrar gameEventRegistrar
    ) {
        this.settlementApi = settlementApi;
        this.gameTimeApi = gameTimeApi;
        this.gameEventRegistrar = gameEventRegistrar;
    }

    @Override
    public ISettlementApi settlements() {
        return settlementApi;
    }

    @Override
    public IGameTimeApi time() {
        return gameTimeApi;
    }

    @Override
    public IGameEventRegistrar gameEventRegistrar() {
        return gameEventRegistrar;
    }
}
