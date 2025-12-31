package Gnava.Game.Events.Simulation;

import Gnava.Game.Settlements.Settlement;

import java.util.concurrent.ThreadLocalRandom;

public class PopulationGrowthEvent extends GameEvent {
    private final int popToAdd;
    private final Settlement target;

    public PopulationGrowthEvent(Settlement target) {
        super(String.format("Population grows in %s", target.getName()), "");

        this.target = target;
        this.popToAdd = calculatePopulationIncrease(target);

        setDescription(String.format("Population of %s grew by %d", target.getName(), popToAdd));
    }

    @Override
    public void happen() {
        target.setMaxPopulation(target.getMaxPopulation() + popToAdd);
        target.setTotalPopulation(target.getTotalPopulation() + popToAdd);
    }

    private static int calculatePopulationIncrease(Settlement target) {
        return target.getMaxPopulation() > 1000
            ? ThreadLocalRandom.current().nextInt(0, 50)
            : ThreadLocalRandom.current().nextInt(0, 200);
    }
}