package Gnava.ModApi;

import Gnava.GameApi.GameEvents.IGameEventRegistrar;

public interface IModContext {
    ISettlementApi settlements();
    IGameTimeApi time();
    IGameEventRegistrar gameEventRegistrar();
}
