package hotciv.common;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public interface WinningStrategy {
    Player checkIfGameOver(GameImpl game);
    void incrementBattlesWonBy(GameImpl game, Player p);

}
