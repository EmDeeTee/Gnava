package Gnava.GameApi.GameEvents;

import java.util.Map;
import java.util.Objects;

public record GameEventResult(
    Map<String, String> translationArguments,
    String fallbackTitle,
    String fallbackDescription
) {
    private static final GameEventResult EMPTY = new GameEventResult(Map.of(), "", "");

    public GameEventResult {
        translationArguments = Map.copyOf(translationArguments);
        fallbackTitle = Objects.requireNonNull(fallbackTitle, "fallbackTitle");
        fallbackDescription = Objects.requireNonNull(fallbackDescription, "fallbackDescription");
    }

    public static GameEventResult empty() {
        return EMPTY;
    }

    public static GameEventResult translated(Map<String, String> arguments) {
        return new GameEventResult(arguments, "", "");
    }

    public static GameEventResult withFallback(
        Map<String, String> arguments,
        String title,
        String description
    ) {
        return new GameEventResult(arguments, title, description);
    }
}
