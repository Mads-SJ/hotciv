package hotciv.variants.strategy;

import hotciv.common.strategy.ValidMoveStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import static hotciv.framework.GameConstants.*;

public class AlphaValidMoveStrategy implements ValidMoveStrategy {
    @Override
    public boolean isMoveValid(GameImpl game, Position from, Position to) {
        boolean isMovingWithinWorldGrid = to.getRow() < WORLDSIZE && to.getColumn() < WORLDSIZE &&
                                          to.getRow() >= 0 && to.getColumn() >= 0;
        if (! isMovingWithinWorldGrid) return false;

        UnitImpl unitToMove = (UnitImpl) game.getUnitAt(from);
        UnitImpl potentialUnitAtToPosition = (UnitImpl) game.getUnitAt(to);

        boolean hasMovesLeft = unitToMove.getMoveCount() > 0;
        if (! hasMovesLeft) return false;

        if (! unitToMove.isMovable()) return false;

        boolean isUnitOwnerThePlayerInTurn = unitToMove.getOwner() == game.getPlayerInTurn();
        if (! isUnitOwnerThePlayerInTurn) return false;

        if (! game.isPassableTerrain(to)) return false;

        boolean isStackingUnits = potentialUnitAtToPosition != null &&
                unitToMove.getOwner() == potentialUnitAtToPosition.getOwner();
        if (isStackingUnits) return false;

        if (! isWithinMoveRange(game, to, from)) return false;

        return true;
    }

    @Override
    public boolean isWithinMoveRange(GameImpl game, Position to, Position from) {
        // Calculating the distance moved horizontally and vertically (these numbers should not exceed 1)
        int rowDist = Math.abs(from.getRow() - to.getRow());
        int columnDist = Math.abs(from.getColumn() - to.getColumn());

        // The move should be within move range (meaning that the unit only moves 1 tile in either direction)
        return rowDist <= MOVE_DISTANCE && columnDist <= MOVE_DISTANCE;
    }
}
