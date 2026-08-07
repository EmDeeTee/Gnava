package Gnava.Core.Mod;

import Gnava.Core.Mod.Context.LoadedMod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class ModRegistry {
    private final List<LoadedMod> loadedMods = new ArrayList<>();

    public void add(LoadedMod mod) {
        loadedMods.add(mod);
    }

    public List<LoadedMod> getLoadedMods() {
        return List.copyOf(loadedMods);
    }
}
