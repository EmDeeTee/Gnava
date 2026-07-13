package Gnava.Core.Repositories;

import Gnava.Core.Settlements.Settlement;

public interface ISettlementRepository extends ISettlementProvider {
    void save(Settlement settlement);
}
