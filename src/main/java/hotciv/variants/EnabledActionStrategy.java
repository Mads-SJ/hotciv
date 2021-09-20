package hotciv.variants;

import hotciv.common.ActionStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class EnabledActionStrategy implements ActionStrategy {
    private GameImpl game;

    public EnabledActionStrategy(GameImpl game) {
        this.game = game;
    }

    @Override
    public void performUnitActionAt(Position p) {
        game.removeUnitAt(p);
        game.createCityAt(p);
    }
}
