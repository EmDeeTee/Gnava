package Gnava.Core.Events.Registered;

import Gnava.Core.Events.AbstractGameEventDefinition;
import Gnava.Core.Events.Contexts.EventContext;
import Gnava.Core.Models.Settlement;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public final class NosferatuAttacksEvent extends AbstractGameEventDefinition {
    @Override
    protected String resolveDescription(EventContext context) {
        return "Nosferatu attacked %s and ate %d residents".formatted(
            context.get("target_settlement", Settlement.class).orElseThrow(),
            context.get("damage", Integer.class).orElseThrow()
        );
    }

    @Override
    protected String resolveTitle(EventContext context) {
        return "Nosferatu!";
    }

    @Override
    public float probability() {
        return 0.1f;
    }

    @Override
    protected void prepare(EventContext context) {
        context.getRandomSettlementAsTarget();

        Settlement targetSettlement = context.get("target_settlement", Settlement.class).orElseThrow();
        int damage = ThreadLocalRandom.current().nextInt(1, Math.min(200, targetSettlement.getTotalPopulation()));
        context.set("damage", damage);
    }
}
