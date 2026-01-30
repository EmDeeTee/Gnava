package Gnava.Interface.Translations;

import Gnava.Interface.Translations.Tables.TranslationTable;
import Gnava.Interface.Translations.Tables.TranslationTableCrustyDutch;
import Gnava.Interface.Translations.Tables.TranslationTableEnglish;

public class TranslationManager {
    private static TranslationManager instance;

    private TranslationTable translationTable;

    public static TranslationManager getInstance() {
        if (instance == null) {
            instance = new TranslationManager();
        }

        return instance;
    }

    public TranslationTable getTranslationTable() {
        return translationTable;
    }

    private void selectTranslationTable() {
        TranslationTable selectedTable;

        if (System.getenv("USE_CRUSTY_DUTCH") != null) {
            selectedTable = new TranslationTableCrustyDutch();
        } else {
            selectedTable = System.getProperty("os.name").startsWith("Windows")
                ? new TranslationTableEnglish()
                : new TranslationTableCrustyDutch();
        }

        translationTable = selectedTable;
    }

    private TranslationManager() {
        selectTranslationTable();
    }
}
