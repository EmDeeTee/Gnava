package Gnava.Launcher;

import Gnava.Desktop.Gnava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.SwingUtilities;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GnavaConfiguration.class);
        Gnava gnava = context.getBean(Gnava.class);

        LOGGER.info("Gnava starting UI");
        // Swing components must be built and shown on the event dispatch thread. Doing it on the
        // main thread happens to work on Windows but leaves dialogs unpainted on X11/Wayland.
        SwingUtilities.invokeLater(gnava::initUi);
    }
}
