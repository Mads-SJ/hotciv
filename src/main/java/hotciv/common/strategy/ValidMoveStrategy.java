package hotciv.common.strategy;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface ValidMoveStrategy {
    boolean isMoveValid(GameImpl game, Position from, Position to);
    boolean isWithinMoveRange(GameImpl game, Position from, Position to);
}
