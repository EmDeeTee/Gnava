package Gnava.Core.GameEvents;

import Gnava.Core.RaceNames.CreatureNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.random.RandomGenerator;

@Configuration
public class GameEventConfiguration {
    @Bean
    public EventRegistry eventRegistry(CreatureNameGenerator creatureNameGenerator) {
        EventRegistry registry = new EventRegistry();
        VanillaEventModule.events(creatureNameGenerator).forEach(registry::register);
        return registry;
    }

    @Bean
    public RandomGenerator gameEventRandomGenerator() {
        return new Random();
    }
}
