package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class SemiTestFactory implements GameFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new GammaActionStrategy();
    }

    @Override
    public AgingStrategy createAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public AttackingStrategy createAttackingStrategy() {
        return new EpsilonAttackingStrategy(new FixedDecisionStrategy());
    }

    @Override
    public WinningStrategy createWinningStrategy() {
        return new EpsilonWinningStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public PopulationStrategy createPopulationStrategy() {
        return new EtaPopulationStrategy();
    }

    @Override
    public ResourceGainStrategy createResourceGainStrategy() {
        return new EtaResourceGainStrategy();
    }

    @Override
    public ValidMoveStrategy createValidMoveStrategy() {
        return new AlphaValidMoveStrategy();
    }

    @Override
    public LegalPositionStrategy createLegalPositionStrategy() {
        return new AlphaLegalPositionStrategy();
    }

    @Override
    public TileStrategy createTileStrategy() {
        return new AlphaTileStrategy();
    }

    @Override
    public UnitStrategy createUnitStrategy() {
        return new AlphaUnitStrategy();
    }
}
