package hotciv.variants;

import hotciv.common.AttackingStrategy;
import hotciv.common.StrengthFactorDecisionStrategy;
import hotciv.framework.Position;

public class EpsilonAttackingStrategy implements AttackingStrategy {
    private StrengthFactorDecisionStrategy decisionStrategy;

    public EpsilonAttackingStrategy(StrengthFactorDecisionStrategy decisionStrategy) {
        this.decisionStrategy = decisionStrategy;
    }

    @Override
    public boolean resolveAttack(Position attacker, Position defender) {
        // attack default strength
        // support added
        // tile multiplied
        // die roll multiplier

        // same for defense

        return true;
    }
}
