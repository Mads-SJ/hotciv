package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class ZetaRealFactory implements GameFactory {
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
        return new ZetaWinningStrategy(new BetaWinningStrategy(), new EpsilonWinningStrategy());
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }
}
