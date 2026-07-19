package Gnava.Desktop.Interface.Translations;

import Gnava.Desktop.Interface.Translations.Tables.TranslationTable;
import Gnava.Desktop.Interface.Translations.Tables.TranslationTableCrustyDutch;
import Gnava.Desktop.Interface.Translations.Tables.TranslationTableEnglish;

import java.util.Objects;

/**
 * @deprecated Use {@link Translator} instead
 */
@Deprecated
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

    // TODO/NOTE: I don't think the translation table should select itself
    private void selectTranslationTable() {
        TranslationTable selectedTable;

        if (Objects.equals(System.getenv("USE_CRUSTY_DUTCH"), "1")) {
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
