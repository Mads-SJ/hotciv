package hotciv.variants;

import hotciv.common.AttackingStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class AlphaAttackingStrategy implements AttackingStrategy {
    @Override
    public boolean resolveAttack(Game game, Position attackerPos, Position defenderPos) {
        return true;
    }
}
