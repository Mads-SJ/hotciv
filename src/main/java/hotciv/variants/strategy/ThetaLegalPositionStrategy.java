package hotciv.variants.strategy;

import hotciv.common.strategy.LegalPositionStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.*;

public class ThetaLegalPositionStrategy implements LegalPositionStrategy {
    AlphaLegalPositionStrategy alphaLegalPositionStrategy;

    public ThetaLegalPositionStrategy() {
        alphaLegalPositionStrategy = new AlphaLegalPositionStrategy();
    }

    @Override
    public boolean isPositionLegal(GameImpl game, Position pos, String unitType) {
        if (! alphaLegalPositionStrategy.isPositionLegal(game, pos, unitType)) return false;

        if (! unitType.equals(SANDWORM)) return true;

        boolean isDesertTile = game.getTileAt(pos).getTypeString().equals(DESERT);
        return isDesertTile;
    }
}
