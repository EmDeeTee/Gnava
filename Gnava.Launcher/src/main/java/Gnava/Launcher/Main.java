package Gnava.Launcher;

import Gnava.Desktop.Gnava;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GnavaConfiguration.class);
        Gnava gnava = context.getBean(Gnava.class);

        gnava.initUi();
    }
}
