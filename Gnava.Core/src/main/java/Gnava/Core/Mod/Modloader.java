package Gnava.Core.Mod;

import Gnava.ModApi.IMod;
import Gnava.ModApi.IModContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ServiceLoader;
import java.util.stream.Stream;

@Service
public class Modloader {
    private final IModContext modContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(Modloader.class);
    
    public Modloader(IModContext modContext) {
        this.modContext = modContext;
    }

    public void initialize() throws IOException {
        LOGGER.info("Checking for mods...");
        Path mods = Path.of("mods");
        LOGGER.info("Looking for .jar files in {}", mods.toAbsolutePath());

        if (!Files.exists(mods)) {
            Files.createDirectories(mods);
            LOGGER.info("No mods found in mods/. Giving up");
            return;
        }

        try (Stream<Path> files = Files.list(mods)) {
            files.filter(path -> path.toString().endsWith(".jar"))
                .forEach(this::loadMod);
        }
    }

    private void loadMod(Path path) {
        LOGGER.info("Processing '{}'...", path);

        try {
            URLClassLoader classLoader = new URLClassLoader(
                new URL[] { path.toUri().toURL() },
                getClass().getClassLoader()
            );

            ServiceLoader<IMod> loader = ServiceLoader.load(IMod.class, classLoader);
            if (loader.findFirst().isPresent()) {
                LOGGER.info("Located and loaded valid service data from '{}'", loader.findFirst().get().getClass().getName());
            } else {
                LOGGER.error("Can't locate a valid IMod implementation is {}", path);
            }

            for (IMod mod : loader) {
                mod.initialise(modContext);
                LOGGER.info("Loaded mod '{}'", mod.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
