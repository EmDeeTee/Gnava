package Gnava.Core.Managers.Settlement;

import Gnava.Core.EventBus.Events.SettlementCreatedEvent;
import Gnava.Core.GameState;
import Gnava.Core.Managers.AbstractGameManager;
import Gnava.Core.Managers.SettlementCreationResult;
import Gnava.Core.Settlements.Requests.CreateSettlementRequest;
import Gnava.Core.Settlements.Settlement;
import Gnava.Core.Repositories.ISettlementRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SettlementManager extends AbstractGameManager {
    private final ISettlementRepository settlementRepository;
    private final SettlementCreationPolicy settlementCreationPolicy;
    private final ApplicationEventPublisher applicationEventPublisher;

    public SettlementManager(
        GameState gameState,
        ISettlementRepository settlementRepository,
        SettlementCreationPolicy settlementCreationPolicy,
        ApplicationEventPublisher applicationEventPublisher
    ) {
        super(gameState);
        this.settlementRepository = settlementRepository;
        this.settlementCreationPolicy = settlementCreationPolicy;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public SettlementCreationResult tryCreateSettlement(CreateSettlementRequest request) {
        SettlementCreationResult result = settlementCreationPolicy.validate(request);
        if (!result.ok()) {
            return result;
        }

        createSettlement(request);
        return result;
    }

    public List<Settlement> getSettlements() {
        return this.settlementRepository.getAll();
    }

    private void createSettlement(CreateSettlementRequest request) {
        Settlement settlement = new Settlement(
            request.name(),
            request.initialPopulation(),
            request.maxPopulation(),
            request.populationType(),
            request.wealthLevel(),
            request.isPlayer()
        );

        this.settlementRepository.save(settlement);
        applicationEventPublisher.publishEvent(new SettlementCreatedEvent(settlement));
    }
}
