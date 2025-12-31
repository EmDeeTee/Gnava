package Gnava.Game.Events.Simulation;

import Gnava.Game.Settlements.Settlement;

public class PopulationGrowthEvent extends GameEvent {
    private final Integer popToAdd;
    private final Settlement target;

    public PopulationGrowthEvent(Integer popToAdd, Settlement target) {
        super(String.format("Population grows in %s", target.getName()), String.format("Population of %s grew by %d", target.getName(), popToAdd));
        this.popToAdd = popToAdd;
        this.target = target;
    }

    @Override
    public void happen() {
        target.setMaxPopulation(target.getMaxPopulation() + popToAdd);
        target.setTotalPopulation(target.getTotalPopulation() + popToAdd);
    }
}
