package Gnava.Core.GameEvents;

import java.util.Map;

public record TranslationData(
    String key,
    Map<String, String> arguments
) {
    public TranslationData {
        arguments = Map.copyOf(arguments);
    }

    public String titleKey() {
        return key + ".title";
    }

    public String descriptionKey() {
        return key + ".description";
    }
}
