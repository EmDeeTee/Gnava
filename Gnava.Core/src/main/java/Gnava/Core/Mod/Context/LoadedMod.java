package Gnava.Core.Mod.Context;

public record LoadedMod(
    String name,
    ClassLoader classLoader
) { }
