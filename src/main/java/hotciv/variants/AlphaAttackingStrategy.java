package hotciv.variants;

import hotciv.common.AttackingStrategy;
import hotciv.framework.Position;

public class AlphaAttackingStrategy implements AttackingStrategy {
    @Override
    public boolean resolveAttack(Position attacker, Position defender) {
        return true;
    }
}
