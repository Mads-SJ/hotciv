package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class ZetaTestFactory implements GameFactory {
    private ZetaWinningStrategy zetaWinningStrategy;

    public ZetaTestFactory() {
        this.zetaWinningStrategy = new ZetaWinningStrategy(new BetaWinningStrategy(), new EpsilonWinningStrategy());
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
        return new AlphaAttackingStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return zetaWinningStrategy;
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }
}
