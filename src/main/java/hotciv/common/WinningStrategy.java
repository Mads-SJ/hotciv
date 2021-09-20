package hotciv.common;

import hotciv.framework.Player;

public interface WinningStrategy {
    Player checkIfGameOver();
}
