package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class EpsilonRealFactory implements GameFactory {
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
        return new EpsilonAttackingStrategy(new DieRollDecisionStrategy());
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWinningStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public PopulationStrategy createPopulationStrategy() {
        return new AlphaPopulationStrategy();
    }

    @Override
    public ResourceGainStrategy createResourceGainStrategy() {
        return new AlphaResourceGainStrategy();
    }
}
