package Gnava.Desktop.Interface.Translations.Tables;

import Gnava.Desktop.Interface.Translations.TranslationKey;

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
        map.put(TranslationKey.NAME, "Name");
        map.put(TranslationKey.MENU_SPELL_BOOK, "Spell book");
        map.put(TranslationKey.POPULATION_TYPE, "Population type:");
        map.put(TranslationKey.ERROR_NAME_CANT_BE_EMPTY, "The name can't be empty");
        map.put(TranslationKey.ERROR, "Error");
        map.put(TranslationKey.CANCEL, "Cancel");
        map.put(TranslationKey.PASS_TIME, "Pass time");
        map.put(TranslationKey.MENU_ACTIONS, "Actions");
        map.put(TranslationKey.MENU_STATISTICS, "Statistics");
        map.put(TranslationKey.MENU_VIEW, "View");
        map.put(TranslationKey.CURRENT_DAY, "Current day:");
        map.put(TranslationKey.EVENTS, "Events");
        map.put(TranslationKey.SETTLEMENTS, "Settlements");

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
