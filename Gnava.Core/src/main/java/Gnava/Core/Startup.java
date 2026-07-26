package Gnava.Core;

import Gnava.Core.Mod.Modloader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public final class Startup {
    private final Modloader modloader;

    public Startup(Modloader modloader) {
        this.modloader = modloader;
    }

    public void start() throws IOException {
        modloader.initialize();
    }
}
