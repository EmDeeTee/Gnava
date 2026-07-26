package Gnava.Core.Mod.Context;

import Gnava.ModApi.IModContext;
import Gnava.ModApi.ISettlementApi;
import org.springframework.stereotype.Service;

@Service
public class ModContext implements IModContext {
    private final SettlementApi settlementApi;

    public ModContext(SettlementApi settlementApi) {
        this.settlementApi = settlementApi;
    }

    @Override
    public ISettlementApi settlements() {
        return settlementApi;
    }
}
