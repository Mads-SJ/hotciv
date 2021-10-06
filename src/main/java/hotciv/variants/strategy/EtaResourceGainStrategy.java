package hotciv.variants.strategy;

import hotciv.common.strategy.ResourceGainStrategy;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.utility.Utility;

import static hotciv.framework.GameConstants.*;

public class EtaResourceGainStrategy implements ResourceGainStrategy {
    @Override
    public void gainResourcesForCityAt(GameImpl game, Position cityPos) {
        CityImpl c = (CityImpl) game.getCityAt(cityPos);

        if (c.getWorkforceFocus().equals(productionFocus)) {
            int productionGain = calculateProductionGain(game, cityPos);
            c.addTreasury(productionGain + 1);
            c.addFood(1);
        }
        else {
            int foodGain = calculateFoodGain(game, cityPos);
            c.addFood(foodGain + 1);
            c.addTreasury(1);
        }
    }

    @Override
    public int calculateProductionGain(GameImpl game, Position cityPos) {
        CityImpl c = (CityImpl) game.getCityAt(cityPos);
        int population = c.getSize() - 1;
        int productionGain = 0;
        Iterable<Position> neighboringTiles = Utility.get8neighborhoodOf(cityPos);

        for (Position p : neighboringTiles) {
            if (population < 1) return productionGain;

            switch (game.getTileAt(p).getTypeString()) {
                case FOREST:
                    productionGain += 3;
                    population--;
                    break;
                case HILLS:
                    productionGain += 2;
                    population--;
                    break;
                case MOUNTAINS:
                    productionGain += 1;
                    population--;
                    break;
            }
        }
        return productionGain;
    }

    @Override
    public int calculateFoodGain(GameImpl game, Position cityPos) {
        CityImpl c = (CityImpl) game.getCityAt(cityPos);
        int population = c.getSize() - 1;
        int foodGain = 0;
        Iterable<Position> neighboringTiles = Utility.get8neighborhoodOf(cityPos);

        for (Position p : neighboringTiles) {
            if (population < 1) return foodGain;

            switch (game.getTileAt(p).getTypeString()) {
                case PLAINS:
                    foodGain += 3;
                    population--;
                    break;
                case OCEANS:
                    foodGain += 1;
                    population--;
                    break;
            }
        }
        return foodGain;
    }
}