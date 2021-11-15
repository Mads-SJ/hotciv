package hotciv.variants.strategy;

import hotciv.common.strategy.ActionStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;

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
            for (Position candidatePosition : Utility.get8neighborhoodOf(p)) {
                Unit potentialUnit = game.getUnitAt(candidatePosition);
                if (potentialUnit == null) continue;

                boolean isPotentialUnitFriendlyUnit = unit.getOwner().equals(potentialUnit.getOwner());
                if (isPotentialUnitFriendlyUnit) continue;

                game.removeUnitAt(candidatePosition);
            }
        }
        else gammaActionStrategy.performUnitActionAt(game, p);
    }
}
