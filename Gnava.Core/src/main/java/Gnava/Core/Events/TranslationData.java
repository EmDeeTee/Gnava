package Gnava.Core.Events;

import java.util.Map;

public record TranslationData(
    String translationKey,
    Map<String, String> context
) { }
