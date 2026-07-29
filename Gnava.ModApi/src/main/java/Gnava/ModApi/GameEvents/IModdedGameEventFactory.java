package Gnava.ModApi.GameEvents;

public interface IModdedGameEventFactory {
    void register(Class<? extends IModdedGameEvent> gameEventType);
}
