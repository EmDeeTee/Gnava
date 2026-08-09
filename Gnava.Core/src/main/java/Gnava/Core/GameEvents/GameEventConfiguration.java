package Gnava.Core.GameEvents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.random.RandomGenerator;

@Configuration
public class GameEventConfiguration {
    @Bean
    public RandomGenerator gameEventRandomGenerator() {
        return new Random();
    }
}
