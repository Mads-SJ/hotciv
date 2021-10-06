package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class EpsilonTestFactory implements GameFactory {
    private EpsilonAttackingStrategy attackingStrategy;
    private EpsilonWinningStrategy winningStrategy;

    public EpsilonTestFactory() {
        this.attackingStrategy = new EpsilonAttackingStrategy(new FixedDecisionStrategy());
        this.winningStrategy = new EpsilonWinningStrategy();
    }

    @Override
    public ActionStrategy createActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return attackingStrategy;
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return winningStrategy;
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }
}