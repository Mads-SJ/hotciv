package hotciv.variants;

import hotciv.common.AgingStrategy;

import static hotciv.framework.GameConstants.AGING_PER_ROUND;

public class BetaAgingStrategy implements AgingStrategy {
    @Override
    public int ageWorld(int currentAge) {
        if (currentAge < -100) {
            return currentAge + AGING_PER_ROUND;
        }
        if (currentAge == -100) {
            return -1;
        }
        if (currentAge == -1) {
            return 1;
        }
        if (currentAge == 1) {
            return 50;
        }
        if (50 <= currentAge && currentAge < 1750) {
            return currentAge + 50;
        }
        if (1750 <= currentAge && currentAge < 1900) {
            return currentAge + 25;
        }
        if (1900 <= currentAge && currentAge < 1970) {
            return currentAge + 5;
        }
        return currentAge + 1; // After year 1970 the world ages with one year per round
    }

}
