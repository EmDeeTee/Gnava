package Gnava.Interface.Translations;

import Gnava.Interface.Translations.Tables.TranslationTable;
import Gnava.Interface.Translations.Tables.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.Tables.TranslationTableEnglish;

public class TranslationManager {
    private static TranslationManager instance;

    private final TranslationTable translationTable = System.getProperty("os.name").startsWith("Windows")
            ? new TranslationTableEnglish()
            : new TranslationTableCrustyDutch();

    public static TranslationManager getInstance() {
        if (instance == null) {
            instance = new TranslationManager();
        }

        return instance;
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }

    private TranslationManager() { }
}
