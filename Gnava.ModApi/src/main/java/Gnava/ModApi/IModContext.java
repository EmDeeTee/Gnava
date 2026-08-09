package Gnava.ModApi;

import Gnava.GameApi.GameEvents.IGameEventRegistrar;
import Gnava.GameApi.IGameTimeApi;
import Gnava.GameApi.ISettlementApi;

public interface IModContext {
    ISettlementApi settlements();
    IGameTimeApi time();
    IGameEventRegistrar gameEventRegistrar();
}
