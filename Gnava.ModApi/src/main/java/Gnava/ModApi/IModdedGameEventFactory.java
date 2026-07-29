package Gnava.ModApi;

public interface IModdedGameEventFactory {
    void register(Class<? extends IModdedGameEvent> gameEventType);
}
