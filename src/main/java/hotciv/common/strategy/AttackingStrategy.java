package hotciv.common.strategy;

import hotciv.framework.Game;
import hotciv.framework.Position;

public interface AttackingStrategy {
    boolean resolveAttack(Game game, Position attackerPos, Position defenderPos);
}
