package Gnava.Core.CommandHandlers;

import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Managers.Settlement.SettlementManager;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateSettlementHandler implements ICommand<CreateSettlementRequest, SettlementCreationResult> {
    private final SettlementManager settlementManager;

    public CreateSettlementHandler(SettlementManager settlementManager) {
        this.settlementManager = settlementManager;
    }

    @Override
    public SettlementCreationResult execute(CreateSettlementRequest request) {
        return settlementManager.tryCreateSettlement(request);
    }
}
