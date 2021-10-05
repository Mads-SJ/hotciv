package hotciv.variants.strategy;

import hotciv.common.strategy.AttackingStrategy;
import hotciv.common.strategy.StrengthFactorDecisionStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.utility.Utility2;

public class EpsilonAttackingStrategy implements AttackingStrategy {
    private StrengthFactorDecisionStrategy decisionStrategy;

    public EpsilonAttackingStrategy(StrengthFactorDecisionStrategy decisionStrategy) {
        this.decisionStrategy = decisionStrategy;
    }

    @Override
    public boolean resolveAttack(Game game, Position attackerPos, Position defenderPos) {
        Unit attackingUnit = game.getUnitAt(attackerPos);
        Unit defendingUnit = game.getUnitAt(defenderPos);

        int combinedAttackingStrength = attackingUnit.getAttackingStrength();
        int combinedDefensiveStrength = defendingUnit.getDefensiveStrength();

        combinedAttackingStrength += Utility2.getFriendlySupport(game, attackerPos, attackingUnit.getOwner());
        combinedDefensiveStrength += Utility2.getFriendlySupport(game, defenderPos, defendingUnit.getOwner());

        combinedAttackingStrength *= Utility2.getTerrainFactor(game, attackerPos);
        combinedDefensiveStrength *= Utility2.getTerrainFactor(game, defenderPos);

        decisionStrategy.roll();

        combinedAttackingStrength *= decisionStrategy.getAttackingEyes();
        combinedDefensiveStrength *= decisionStrategy.getDefendingEyes();

        return combinedAttackingStrength > combinedDefensiveStrength;
    }

    public StrengthFactorDecisionStrategy getDecisionStrategy() {
        return decisionStrategy;
    }
}
