package hotciv.variants.strategy;

import hotciv.common.strategy.PopulationStrategy;
import hotciv.standard.CityImpl;

public class EtaPopulationStrategy implements PopulationStrategy {
    @Override
    public void increaseCityPopulation(CityImpl c) {
        if (c.getFood() > 5 + c.getSize() * 3 && c.getSize() < 9) {
            c.increasePopulation();
            c.resetFood();
        }
    }
}
