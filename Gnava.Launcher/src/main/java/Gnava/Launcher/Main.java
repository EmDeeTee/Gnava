package Gnava.Launcher;

import Gnava.Desktop.Gnava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GnavaConfiguration.class);
        Gnava gnava = context.getBean(Gnava.class);

        LOGGER.info("Gnava starting UI");
        gnava.initUi();
    }
}
