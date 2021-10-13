package hotciv.variants.strategy;

import hotciv.common.strategy.ValidMoveStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.*;

public class ThetaValidMoveStrategy implements ValidMoveStrategy {
    private ValidMoveStrategy alphaValidMoveStrategy;

    public  ThetaValidMoveStrategy() {
        alphaValidMoveStrategy = new AlphaValidMoveStrategy();
    }
    @Override
    public boolean isMoveValid(GameImpl game, Position from, Position to) {
        if (! alphaValidMoveStrategy.isMoveValid(game, from, to)) return false;

        boolean isSandwormAtFromPos = game.getUnitAt(from).getTypeString().equals(SANDWORM);
        if (isSandwormAtFromPos) {
            boolean isDesertTileAtToPos = game.getTileAt(to).getTypeString().equals(DESERT);
            if (! isDesertTileAtToPos) return false;
        }
        return true;
    }

    @Override
    public boolean isWithinMoveRange(GameImpl game, Position from, Position to) {
        return false;
    }
}
