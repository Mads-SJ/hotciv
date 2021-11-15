package hotciv.variants.strategy;

import hotciv.common.strategy.UnitStrategy;

import static hotciv.framework.GameConstants.*;

public class ThetaUnitStrategy implements UnitStrategy {
    private AlphaUnitStrategy alphaUnitStrategy;

    public ThetaUnitStrategy() {
        alphaUnitStrategy = new AlphaUnitStrategy();
    }

    @Override
    public int initializeMoveCount(String typeString) {
        if (typeString.equals(SANDWORM)) return SANDWORM_MOVE_COUNT;
        return alphaUnitStrategy.initializeMoveCount(typeString);
    }

    @Override
    public int initializeAttackingStrength(String typeString) {
        if (typeString.equals(SANDWORM)) return 0;
        return alphaUnitStrategy.initializeAttackingStrength(typeString);
    }

    @Override
    public int initializeDefensiveStrength(String typeString) {
        if (typeString.equals(SANDWORM)) return 10;
        return alphaUnitStrategy.initializeDefensiveStrength(typeString);
    }

    @Override
    public boolean isUnitValid(String typeString) {
        if (typeString.equals(SANDWORM)) return true;
        return alphaUnitStrategy.isUnitValid(typeString);
    }
}
