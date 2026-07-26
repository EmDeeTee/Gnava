package Gnava.Core.Mod.Context;

import Gnava.ModApi.IModContext;
import Gnava.ModApi.IGameTimeApi;
import Gnava.ModApi.ISettlementApi;
import org.springframework.stereotype.Service;

@Service
public class ModContext implements IModContext {
    private final SettlementApi settlementApi;
    private final GameTimeApi gameTimeApi;

    public ModContext(SettlementApi settlementApi, GameTimeApi gameTimeApi) {
        this.settlementApi = settlementApi;
        this.gameTimeApi = gameTimeApi;
    }

    @Override
    public ISettlementApi settlements() {
        return settlementApi;
    }

    @Override
    public IGameTimeApi time() {
        return gameTimeApi;
    }
}
