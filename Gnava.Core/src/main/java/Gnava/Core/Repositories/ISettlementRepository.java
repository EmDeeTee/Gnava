package Gnava.Core.Repositories;

import Gnava.Core.Models.Settlement;

public interface ISettlementRepository extends ISettlementProvider {
    void save(Settlement settlement);
}
