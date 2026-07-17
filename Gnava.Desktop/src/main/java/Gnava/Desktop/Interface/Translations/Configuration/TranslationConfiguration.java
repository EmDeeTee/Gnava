package Gnava.Desktop.Interface.Translations.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class TranslationConfiguration {
    @Bean
    public Locale locale() {
        if ("1".equals(System.getenv("USE_CRUSTY_DUTCH"))) {
            return Locale.of("crustyDutch");
        }

        return System.getProperty("os.name").startsWith("Windows")
            ? Locale.ENGLISH
            : Locale.of("crustyDutch");
    }
}
