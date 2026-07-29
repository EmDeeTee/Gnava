package Gnava.Core.GameEvents;

import Gnava.Core.GameEvents.Contexts.SettlementEventContext;

public class TestEvent extends AbstractGameEvent<SettlementEventContext> {
    @Override
    protected String getTitleTranslationKey() {
        return "hello";
    }

    @Override
    protected String getDescriptionTranslationKey() {
        return "";
    }
}
