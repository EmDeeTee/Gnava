package Gnava.ModApi;

import Gnava.ModApi.GameEvents.IModdedGameEventFactory;

public interface IModContext {
    ISettlementApi settlements();
    IGameTimeApi time();
    IModdedGameEventFactory moddedEvents();
}
