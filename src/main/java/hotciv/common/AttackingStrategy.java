package hotciv.common;

import hotciv.framework.Position;

public interface AttackingStrategy {
    boolean resolveAttack(Position attacker, Position defender);
}
