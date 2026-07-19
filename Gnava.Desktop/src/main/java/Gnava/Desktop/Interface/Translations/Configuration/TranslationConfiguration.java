package Gnava.Desktop.Interface.Translations.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class TranslationConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(TranslationConfiguration.class);

    @Bean
    public Locale locale() {
        boolean forceUseCrustyDutch = "1".equals(System.getenv("USE_CRUSTY_DUTCH"));
        if (forceUseCrustyDutch) {
            LOGGER.info("USE_CRUSTY_DUTCH is set to true. Forcing crusty dutch");
            return Locale.of("crustyDutch");
        }

        LOGGER.info("Guessing the locale...");
        Locale selectedLocale = System.getProperty("os.name").startsWith("Windows")
            ? Locale.ENGLISH
            : Locale.of("crustyDutch");

        LOGGER.info("Selected {} as locale", selectedLocale);
        return selectedLocale;
    }
}
