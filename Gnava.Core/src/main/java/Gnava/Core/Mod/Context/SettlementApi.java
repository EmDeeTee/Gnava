package Gnava.Core.Mod.Context;

import Gnava.Core.Repositories.ISettlementProvider;
import Gnava.ModApi.ISettlementApi;
import org.springframework.stereotype.Service;

@Service
public class SettlementApi implements ISettlementApi {
    private final ISettlementProvider settlementProvider;

    public SettlementApi(ISettlementProvider settlementProvider) {
        this.settlementProvider = settlementProvider;
    }

    @Override
    public int getCount() {
        return settlementProvider.count();
    }
}
