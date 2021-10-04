package hotciv.variants.strategy;

import hotciv.common.strategy.AgingStrategy;

import static hotciv.framework.GameConstants.AGING_PER_ROUND;


public class AlphaAgingStrategy implements AgingStrategy {
    @Override
    public int ageWorld(int currentAge) {
        return currentAge + AGING_PER_ROUND;
    }

}
