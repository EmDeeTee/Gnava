package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Events.IGameEventDefinition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class KEvent3 extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "gg";
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "KApocalypse";
    }

    @Override
    public boolean isStoryEvent() {
        return true;
    }

    @Override
    public boolean firesOnce() {
        return true;
    }

    @Override
    public float probability() {
        return 0.03f;
    }

    @Override
    public List<Class<? extends IGameEventDefinition<?>>> prerequisites() {
        return List.of(KEvent2.class);
    }
}
