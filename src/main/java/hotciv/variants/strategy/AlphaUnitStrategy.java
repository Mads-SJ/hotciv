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
        switch (typeString) {
            case SETTLER:
                return 0;
            case ARCHER:
                return 2;
            case LEGION:
                return 4;
        }
        return -1;
    }

    @Override
    public int initializeDefensiveStrength(String typeString) {
        switch (typeString) {
            case SETTLER:
            case ARCHER:
                return 3;
            case LEGION:
                return 2;
        }
        return -1;
    }

    @Override
    public boolean isUnitValid(String typeString) {
        switch(typeString) {
            case SETTLER:
            case ARCHER:
            case LEGION:
                return true;
        }
        return false;
    }
}
