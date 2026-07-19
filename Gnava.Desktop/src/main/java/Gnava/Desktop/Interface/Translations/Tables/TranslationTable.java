package Gnava.Desktop.Interface.Translations.Tables;

import Gnava.Desktop.Interface.Translations.TranslationKey;

import java.util.Map;

@Deprecated(since = "1.0.0")
public interface TranslationTable {
    String language();

    Map<TranslationKey, String> table();

    default String t(TranslationKey key) {
        return table().getOrDefault(key, "%s_NO_STRING_FOR(%s)".formatted(language(), key));
    }
}
