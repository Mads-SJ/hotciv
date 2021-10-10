package hotciv.common.factory;

import hotciv.common.strategy.*;

public interface GameFactory {
    ActionStrategy createActionStrategy();
    AgingStrategy createAgingStrategy();
    AttackingStrategy createAttackingStrategy();
    WinningStrategy createWinningStrategy();
    WorldLayoutStrategy createWorldLayoutStrategy();
    PopulationStrategy createPopulationStrategy();
    ResourceGainStrategy createResourceGainStrategy();
    ValidMoveStrategy createValidMoveStrategy();
    LegalPositionStrategy createLegalPositionStrategy();
}
