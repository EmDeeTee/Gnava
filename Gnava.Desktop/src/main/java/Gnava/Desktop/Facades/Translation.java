package Gnava.Desktop.Facades;

import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;
import Gnava.Desktop.Interface.Translations.Translator;

/**
 * @deprecated Use {@link Translator} instead
 */
@Deprecated
public final class Translation {
    public static String t(TranslationKey key) {
        return TranslationManager.getInstance().getTranslationTable().t(key);
    }
}
