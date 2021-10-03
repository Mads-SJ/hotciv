package hotciv.variants;

import hotciv.common.StrengthFactorDecisionStrategy;

public class FixedDecisionStrategy implements StrengthFactorDecisionStrategy {
    private int attackingEyes;
    private int defendingEyes;

    public FixedDecisionStrategy() {
        attackingEyes = 1;
        defendingEyes = 1;
    }

    public void setAttackingEyes(int eyes) {
        attackingEyes = eyes;
    }

    public void setDefendingEyes(int eyes) {
        defendingEyes = eyes;
    }

    @Override
    public int getAttackingEyes() {
        return attackingEyes;
    }

    @Override
    public int getDefendingEyes() {
        return defendingEyes;
    }
}
