package hotciv.common.strategy;

import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;

public interface LegalPositionStrategy {
    boolean isPositionLegal(GameImpl game, Position pos, String unitType);
}
