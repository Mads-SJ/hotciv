package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class DeltaFactory implements GameFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new AlphaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new AlphaAttackingStrategy();
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new BetaWinningStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public PopulationStrategy createPopulationStrategy() {
        return new AlphaPopulationStrategy();
    }
}
