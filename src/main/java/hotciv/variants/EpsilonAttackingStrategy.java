package hotciv.variants;

import hotciv.common.AttackingStrategy;
import hotciv.common.StrengthFactorDecisionStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.utility.Utility2;
import jdk.jshell.execution.Util;

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

        // attack default strength
        // support added
        combinedAttackingStrength += Utility2.getFriendlySupport(game, attackerPos, attackingUnit.getOwner());
        combinedDefensiveStrength += Utility2.getFriendlySupport(game, defenderPos, defendingUnit.getOwner());
        // tile multiplied
        combinedAttackingStrength *= Utility2.getTerrainFactor(game, attackerPos);
        combinedDefensiveStrength *= Utility2.getTerrainFactor(game, defenderPos);
        // die roll multiplier

        // same for defense

        return combinedAttackingStrength > combinedDefensiveStrength;
    }
}
