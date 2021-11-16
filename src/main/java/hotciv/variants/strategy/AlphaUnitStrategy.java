package hotciv.variants.strategy;

import hotciv.common.strategy.UnitStrategy;

import static hotciv.framework.GameConstants.*;

public class AlphaUnitStrategy implements UnitStrategy {
    @Override
    public int initializeMoveCount(String typeString) {
        return STANDARD_MOVE_COUNT;
    }

    @Override
    public int initializeAttackingStrength(String typeString) {
        return switch (typeString) {
            case SETTLER -> 0;
            case ARCHER -> 2;
            case LEGION -> 4;
            default -> -1;
        };
    }

    @Override
    public int initializeDefensiveStrength(String typeString) {
        return switch (typeString) {
            case SETTLER, ARCHER -> 3;
            case LEGION -> 2;
            default -> -1;
        };
    }

    @Override
    public int getCostOfUnit(String typeString) {
        return switch (typeString) {
            case ARCHER -> ARCHER_COST;
            case LEGION -> LEGION_COST;
            case SETTLER -> SETTLER_COST;
            default -> 0;
        };
    }

    @Override
    public boolean isUnitValid(String typeString) {
        return switch (typeString) {
            case SETTLER, ARCHER, LEGION -> true;
            default -> false;
        };
    }
}
