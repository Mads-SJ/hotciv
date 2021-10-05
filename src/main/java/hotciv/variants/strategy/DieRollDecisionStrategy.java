package hotciv.variants.strategy;

import hotciv.common.strategy.StrengthFactorDecisionStrategy;

import java.util.Random;

public class DieRollDecisionStrategy implements StrengthFactorDecisionStrategy {
    private int attackingEyes;
    private int defendingEyes;
    private Random random;

    public DieRollDecisionStrategy() {
        attackingEyes = 1;
        defendingEyes = 1;
        random = new Random();
    }

    @Override
    public int getAttackingEyes() {
        return attackingEyes;
    }

    @Override
    public int getDefendingEyes() {
        return defendingEyes;
    }

    @Override
    public void roll() {
        attackingEyes = random.nextInt(6) + 1;
        defendingEyes = random.nextInt(6) + 1;
    }
}
