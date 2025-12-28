package Gnava.Interface.Translations;

import java.util.Map;

public class TranslationTableCrustyDutch implements TranslationTable {
    private final Map<TranslationKey, String> table = Map.ofEntries(
        Map.entry(TranslationKey.GKINGDOMS, "Koninkrijken van Gnava"),
        Map.entry(TranslationKey.CREATE_SETTLEMENT, "Creëren Schikking"),
        Map.entry(TranslationKey.WELCOME_MESSAGE, "Welkom, naar de Koninkrijken van Gnava. Jij zijn A godachtig wezen toezicht houden dit wereld. <br><br> Creëren jouw schikking en hulp het worden de meest krachtig en voorspoedig land in de rijk.")
    );

    @Override
    public String language() {
        return "CNL";
    }

    @Override
    public Map<TranslationKey, String> table() {
        return table;
    }
}
