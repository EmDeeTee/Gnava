package Gnava.Game;

public class Settlement {
    private final String name;
    private final int totalPopulation;
    private final int maxPopulation;

    public Settlement(String name, int totalPopulation, int maxPopulation) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.maxPopulation = maxPopulation;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }
}
