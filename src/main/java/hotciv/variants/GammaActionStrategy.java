package hotciv.variants;

import hotciv.common.ActionStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import static hotciv.framework.GameConstants.*;


public class GammaActionStrategy implements ActionStrategy {
    @Override
    public void performUnitActionAt(GameImpl game, Position p) {
        UnitImpl unit = (UnitImpl) game.getUnitAt(p);
        if (unit.getTypeString().equals(SETTLER)) {
            game.removeUnitAt(p);
            game.createCityAt(p);
        }
        else if (unit.getTypeString().equals(ARCHER)) {
            unit.changeDefensiveStrength();
            unit.changeMovable();
        }
    }
}
