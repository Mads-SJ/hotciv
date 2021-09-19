package hotciv.variants;

import hotciv.common.ActionStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class DisabledActionStrategy implements ActionStrategy {
    private GameImpl game;

    public DisabledActionStrategy(GameImpl game) {
        this.game = game;
    }

    public void performUnitActionAt(Position p) {
        // do nothing
    }
}
