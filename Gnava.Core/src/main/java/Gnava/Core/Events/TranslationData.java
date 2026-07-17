package Gnava.Core.Events;

import java.util.Map;

public record TranslationData(
    String titleKey,
    String descriptionKey,
    Map<String, String> titleContext,
    Map<String, String> descriptionContext
) {
//    public TranslationData {
//        titleContext = Map.copyOf(titleContext);
//        descriptionContext = Map.copyOf(descriptionContext);
//    }
}
