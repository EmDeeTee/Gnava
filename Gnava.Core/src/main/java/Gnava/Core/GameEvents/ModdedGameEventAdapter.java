package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.EventContext;
import Gnava.ModApi.GameEvents.IModEventContext;
import Gnava.ModApi.GameEvents.IModdedGameEvent;

public final class ModdedGameEventAdapter<T extends IModEventContext> extends AbstractGameEvent<T> {
    private final IModdedGameEvent modEvent;

    public ModdedGameEventAdapter(IModdedGameEvent modEvent) {
        this.modEvent = modEvent;
    }

    @Override
    protected void apply(EventContext context) {
        System.out.println("Called exec on modded adapter");
//        modEvent.execute(context);
    }

    @Override
    protected String getTitleTranslationKey() {
        return "events." + "MODDED_EVENT" + ".title";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "events." + "MODDED_EVENT" + ".description";
    }

    @Override
    public float probability() {
        return modEvent.probability();
    }

    @Override
    public boolean firesOnce() {
        return modEvent.firesOnce();
    }
}