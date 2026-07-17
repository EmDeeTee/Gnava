package Gnava.Core.Events;

import org.jspecify.annotations.Nullable;

import java.util.Map;

public record TranslationData(
    String titleKey,
    String descriptionKey,
    @Nullable Map<String, String> context
) {
    public TranslationData {
        context = context == null ? null : Map.copyOf(context);
    }
}
