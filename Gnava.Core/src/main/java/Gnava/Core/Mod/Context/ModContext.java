package Gnava.Core.Mod.Context;

import Gnava.ModApi.IModContext;
import Gnava.ModApi.IGameTimeApi;
import Gnava.ModApi.IModdedGameEventFactory;
import Gnava.ModApi.ISettlementApi;
import org.springframework.stereotype.Service;

@Service
public class ModContext implements IModContext {
    private final SettlementApi settlementApi;
    private final GameTimeApi gameTimeApi;
    private final IModdedGameEventFactory moddedGameEventFactory;

    public ModContext(
        SettlementApi settlementApi,
        GameTimeApi gameTimeApi,
        IModdedGameEventFactory moddedGameEventFactory
    ) {
        this.settlementApi = settlementApi;
        this.gameTimeApi = gameTimeApi;
        this.moddedGameEventFactory = moddedGameEventFactory;
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
    public IModdedGameEventFactory moddedEvents() {
        return moddedGameEventFactory;
    }
}
