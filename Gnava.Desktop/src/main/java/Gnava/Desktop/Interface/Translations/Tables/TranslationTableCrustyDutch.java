package Gnava.Desktop.Interface.Translations.Tables;

import Gnava.Desktop.Interface.Translations.TranslationKey;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

@Deprecated(since = "1.0.0")
public class TranslationTableCrustyDutch implements TranslationTable {
    private final Map<TranslationKey, String> table;

    public TranslationTableCrustyDutch() {
        EnumMap<TranslationKey, String> map = new EnumMap<>(TranslationKey.class);

        map.put(TranslationKey.GKINGDOMS, "Koninkrijken van Gnava");
        map.put(TranslationKey.CREATE_SETTLEMENT, "Creëren Schikking");
        map.put(TranslationKey.WELCOME_MESSAGE, "Welkom, naar de Koninkrijken van Gnava. Jij zijn A godachtig wezen toezicht houden dit wereld. <br><br> Creëren jouw schikking en hulp het worden de meest krachtig en voorspoedig land in de rijk.");
        map.put(TranslationKey.NAME, "Naam");
        map.put(TranslationKey.MENU_SPELL_BOOK, "Toverboek");
        map.put(TranslationKey.POPULATION_TYPE, "Bevolking type:");
        map.put(TranslationKey.ERROR_NAME_CANT_BE_EMPTY, "De naam kan niet zijn leeg");
        map.put(TranslationKey.ERROR, "FOUT !");
        map.put(TranslationKey.CANCEL, "Annuleren");
        map.put(TranslationKey.PASS_TIME, "Doorgang tijd");
        map.put(TranslationKey.MENU_ACTIONS, "Acties");
        map.put(TranslationKey.MENU_STATISTICS, "Statistieken");
        map.put(TranslationKey.MENU_VIEW, "Weergave");
        map.put(TranslationKey.CURRENT_DAY, "Huidig dag:");
        map.put(TranslationKey.EVENTS, "Evenementen");
        map.put(TranslationKey.SETTLEMENTS, "Schikkingen");

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
