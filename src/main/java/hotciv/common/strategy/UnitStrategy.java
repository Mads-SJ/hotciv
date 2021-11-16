package hotciv.common.strategy;

public interface UnitStrategy {
    int initializeMoveCount(String typeString);
    int initializeAttackingStrength(String typeString);
    int initializeDefensiveStrength(String typeString);
    int getCostOfUnit(String typeString);
    boolean isUnitValid(String typeString);
}
