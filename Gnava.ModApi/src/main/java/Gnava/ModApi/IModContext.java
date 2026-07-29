package Gnava.ModApi;

public interface IModContext {
    ISettlementApi settlements();
    IGameTimeApi time();
    IModdedGameEventFactory moddedEvents();
}
