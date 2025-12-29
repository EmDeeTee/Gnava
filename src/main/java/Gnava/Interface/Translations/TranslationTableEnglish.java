package Gnava.Interface.Translations;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class TranslationTableEnglish implements TranslationTable {
    private final Map<TranslationKey, String> table;

    public TranslationTableEnglish() {
        EnumMap<TranslationKey, String> map = new EnumMap<>(TranslationKey.class);

        map.put(TranslationKey.GKINGDOMS, "Kingdoms of Gnava");
        map.put(TranslationKey.CREATE_SETTLEMENT, "Create Settlement");
        map.put(TranslationKey.WELCOME_MESSAGE, "Welcome, to the Kingdoms of Gnava. You are a god-like being overseeing this world. <br><br> Create your settlement and help it become the most powerful and prosperous land in the realm.");

        table = Collections.unmodifiableMap(map);
    }

    @Override
    public String language() {
        return "EN";
    }

    @Override
    public Map<TranslationKey, String> table() {
        return table;
    }
}
