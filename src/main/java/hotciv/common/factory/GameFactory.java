package hotciv.common.factory;

import hotciv.common.strategy.*;

public interface GameFactory {
    ActionStrategy createActionStrategy();
    AgingStrategy createAgingStrategy();
    AttackingStrategy createAttackingStrategy();
    WinningStrategy createWinningStrategy();
    WorldLayoutStrategy createWorldLayoutStrategy();
    ValidMoveStrategy createValidMoveStrategy();
    LegalPositionStrategy createLegalPositionStrategy();
}
