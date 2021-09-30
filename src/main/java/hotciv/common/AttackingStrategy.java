package hotciv.common;

import hotciv.framework.Position;

public interface AttackingStrategy {
    boolean resolveBattle(Position attacker, Position defender);
}
