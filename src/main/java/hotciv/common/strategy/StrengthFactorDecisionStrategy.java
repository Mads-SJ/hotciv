package hotciv.common.strategy;

public interface StrengthFactorDecisionStrategy { //TODO: bør "roll" være en del af interfacet?
    int getAttackingEyes();
    int getDefendingEyes();
    void roll();
}
