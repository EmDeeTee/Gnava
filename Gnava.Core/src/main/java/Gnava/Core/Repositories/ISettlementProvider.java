package Gnava.Core.Repositories;

import Gnava.Core.Models.Settlement;

import java.util.List;

public interface ISettlementProvider {
    List<Settlement> getAll();
    int count();
    Settlement getRandom();
    Settlement getPlayerSettlement();
}
