package Gnava.Desktop.Interface.Translations;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public final class Translator {
    private final ResourceBundle bundle;
    private final Locale locale;

    public Translator(Locale locale) {
        this.bundle = ResourceBundle.getBundle("locale.messages", locale);
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String t(String key) {
        return bundle.getString(key);
    }

    public String t(String key, Map<String, String> context) {
        String translation = bundle.getString(key);

        for (Map.Entry<String, String> entry : context.entrySet()) {
            translation = translation.replace(
                "{" + entry.getKey() + "}",
                entry.getValue()
            );
        }

        return translation;
    }
}