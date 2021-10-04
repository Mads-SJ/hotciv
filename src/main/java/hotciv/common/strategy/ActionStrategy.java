package hotciv.common.strategy;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface ActionStrategy {
    void performUnitActionAt(GameImpl game, Position p);
}
