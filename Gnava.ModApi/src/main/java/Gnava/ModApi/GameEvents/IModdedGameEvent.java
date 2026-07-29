package Gnava.ModApi.GameEvents;

public interface IModdedGameEvent {
    String sayHelloFromMod();

    default float probability() {
        return 1.0f;
    }

    default boolean firesOnce() {
        return false;
    }
}
