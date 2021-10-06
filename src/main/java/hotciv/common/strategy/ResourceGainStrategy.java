package hotciv.common.strategy;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface ResourceGainStrategy {
    void gainResourcesForCityAt(GameImpl game, Position p);
    int calculateProductionGain(Position p);
    int calculateFoodGain(Position p);
}
