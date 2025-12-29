package Gnava.Interface.Translations;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class TranslationTableCrustyDutch implements TranslationTable {
    private final Map<TranslationKey, String> table;

    public TranslationTableCrustyDutch() {
        EnumMap<TranslationKey, String> map = new EnumMap<>(TranslationKey.class);

        map.put(TranslationKey.GKINGDOMS, "Koninkrijken van Gnava");
        map.put(TranslationKey.CREATE_SETTLEMENT, "Creëren Schikking");
        map.put(TranslationKey.WELCOME_MESSAGE, "Welkom, naar de Koninkrijken van Gnava. Jij zijn A godachtig wezen toezicht houden dit wereld. <br><br> Creëren jouw schikking en hulp het worden de meest krachtig en voorspoedig land in de rijk.");

        table = Collections.unmodifiableMap(map);
    }

    @Override
    public String language() {
        return "CNL";
    }

    @Override
    public Map<TranslationKey, String> table() {
        return table;
    }
}
