package hotciv.variants;

import hotciv.common.ActionStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class EnabledActionStrategy implements ActionStrategy {
    @Override
    public void performUnitActionAt(GameImpl game, Position p) {
        game.removeUnitAt(p);
        game.createCityAt(p);
    }
}
