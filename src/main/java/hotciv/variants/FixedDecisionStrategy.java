package hotciv.variants;

import hotciv.common.StrengthFactorDecisionStrategy;

public class FixedDecisionStrategy implements StrengthFactorDecisionStrategy {
    private int eyes;

    public FixedDecisionStrategy() {
        eyes = 0;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    @Override
    public int getEyes() {
        return eyes;
    }
}
