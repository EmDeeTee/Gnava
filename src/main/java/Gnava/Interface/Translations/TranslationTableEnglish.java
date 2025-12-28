package Gnava.Interface.Translations;

import java.util.Map;

public class TranslationTableEnglish implements TranslationTable {
    private final Map<TranslationKey, String> table = Map.ofEntries(
        Map.entry(TranslationKey.GKINGDOMS, "Kingdoms of Gnava"),
        Map.entry(TranslationKey.CREATE_SETTLEMENT, "Create Settlement"),
        Map.entry(TranslationKey.WELCOME_MESSAGE, "Welcome, to the Kingdoms of Gnava. You are a god-like being overseeing this world. <br><br> Create your settlement and help it become the most powerful and prosperous land in the realm.")
    );

    @Override
    public String language() {
        return "EN";
    }

    @Override
    public Map<TranslationKey, String> table() {
        return table;
    }
}
