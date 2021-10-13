package hotciv.variants.strategy;

import hotciv.common.strategy.LegalPositionStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;

public class AlphaLegalPositionStrategy implements LegalPositionStrategy {
    @Override
    public boolean isPositionLegal(GameImpl game, Position pos, String unitType) {
        return game.isPassableTerrain(pos);
    }
}
