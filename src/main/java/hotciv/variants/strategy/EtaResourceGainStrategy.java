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
        int result = 0;
        Iterable<Position> neighboringTiles = Utility.get8neighborhoodOf(cityPos);

        for (Position p : neighboringTiles) {
            if (population > 0 && game.getTileAt(p).getTypeString().equals(FOREST)) {
                result += 3;
                population--;
            }
            else if (population > 0 && game.getTileAt(p).getTypeString().equals(HILLS)) {
                result += 2;
                population--;
            }
        }

        return 0;
    }

    @Override
    public int calculateFoodGain(GameImpl game, Position cityPos) {
        return 0;
    }
}
