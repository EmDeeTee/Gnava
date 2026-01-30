package Gnava.Interface.Translations.Tables;

import Gnava.Interface.Translations.TranslationKey;

import java.util.Map;

public interface TranslationTable {
    String language();

    Map<TranslationKey, String> table();

    default String t(TranslationKey key) {
        return table().getOrDefault(key, "%s_NO_STRING_FOR(%s)".formatted(language(), key));
    }
}
