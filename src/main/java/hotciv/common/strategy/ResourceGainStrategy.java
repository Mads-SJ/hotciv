package hotciv.common.strategy;

import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

public interface ResourceGainStrategy {
    void gainResourcesForCityAt(GameImpl game, Position p);
    int calculateProductionGain(GameImpl game, Position p);
    int calculateFoodGain(GameImpl game, Position p);
}
