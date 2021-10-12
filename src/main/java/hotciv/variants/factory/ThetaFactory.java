package hotciv.variants.factory;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.*;
import hotciv.variants.strategy.*;

public class ThetaFactory implements GameFactory {
    @Override
    public ActionStrategy createActionStrategy() {
        return new GammaActionStrategy();
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
        return new AlphaWinningStrategy();
    }

    @Override
    public WorldLayoutStrategy createWorldLayoutStrategy() {
        return new ThetaWorldLayoutStrategy();
    }

    @Override
    public PopulationStrategy createPopulationStrategy() {
        return new AlphaPopulationStrategy();
    }

    @Override
    public ResourceGainStrategy createResourceGainStrategy() {
        return new AlphaResourceGainStrategy();
    }

    @Override
    public ValidMoveStrategy createValidMoveStrategy() {
        return new ThetaValidMoveStrategy();
    }

    @Override
    public LegalPositionStrategy createLegalPositionStrategy() {
        return new ThetaLegalPositionStrategy();
    }

    @Override
    public TileStrategy createTileStrategy() {
        return new AlphaTileStrategy();
    }
}
