package Gnava.Desktop.Interface.Popups.Buttons;

import Gnava.Desktop.Interface.Elements.GnavaButton;
import Gnava.Desktop.Interface.Translations.TranslationKey;
import Gnava.Desktop.Interface.Translations.TranslationManager;

public class ButtonCancel extends GnavaButton {
    public ButtonCancel() {
        super(TranslationManager.getInstance().getTranslationTable().t(TranslationKey.CANCEL));
    }
}
