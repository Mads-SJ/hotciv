package hotciv.variants.strategy;

import hotciv.common.strategy.ActionStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import static hotciv.framework.GameConstants.SANDWORM;

public class ThetaActionStrategy implements ActionStrategy {
    private GammaActionStrategy gammaActionStrategy;

    public ThetaActionStrategy() {
        gammaActionStrategy = new GammaActionStrategy();
    }

    @Override
    public void performUnitActionAt(GameImpl game, Position p) {
        Unit unit = game.getUnitAt(p);

        if (unit.getTypeString().equals(SANDWORM)) {
            game.removeUnitAt(new Position(8, 7));
        }
        else gammaActionStrategy.performUnitActionAt(game, p);
    }
}
