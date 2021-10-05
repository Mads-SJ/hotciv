package hotciv.variants.strategy;

import hotciv.common.strategy.AttackingStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaAttackingStrategy implements AttackingStrategy {
    @Override
    public boolean resolveAttack(Game game, Position attackerPos, Position defenderPos) {
        return true;
    }
}
