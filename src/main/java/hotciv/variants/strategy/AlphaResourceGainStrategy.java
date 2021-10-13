package hotciv.variants.strategy;

import hotciv.common.strategy.ResourceGainStrategy;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.PRODUCTION_AMOUNT;

public class AlphaResourceGainStrategy implements ResourceGainStrategy {

    @Override
    public void gainResourcesForCityAt(GameImpl game, Position p) {
        CityImpl c = (CityImpl) game.getCityAt(p);
        c.addTreasury(PRODUCTION_AMOUNT);
    }

    @Override
    public int calculateProductionGain(GameImpl game, Position p) {
        return 0;
    }

    @Override
    public int calculateFoodGain(GameImpl game, Position p) {
        return 0;
    }

}
