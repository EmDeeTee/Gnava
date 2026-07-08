package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Contexts.SettlementEventContext;
import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class NosferatuEvent extends AbstractGameEventDefinition<SettlementEventContext> {
    @Override
    protected String resolveDescription(SettlementEventContext context) {
        return "Nosferatu attacked %s and ate %d residents".formatted(
            context.get("target_settlement", Settlement.class).orElseThrow(),
            context.get("damage", Integer.class).orElseThrow()
        );
    }

    @Override
    protected String resolveTitle(SettlementEventContext context) {
        return "Nosferatu!";
    }

    @Override
    public float probability() {
        return 0.1f;
    }

    @Override
    protected void prepare(SettlementEventContext context) {
        context.getRandomSettlementAsTarget();

        Settlement targetSettlement = context.get("target_settlement", Settlement.class).orElseThrow();
        // FIXME: I think this crashes early in the game when population is 1 or 0?
        context.set("damage", ThreadLocalRandom.current().nextInt(1, Math.min(200, targetSettlement.getTotalPopulation())));
    }
}
