package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class EpsilonWinningStrategy implements WinningStrategy {

    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (game.getRedAttackingWins() == 3) return Player.RED;
        if (game.getBlueAttackingWins() == 3) return Player.BLUE;
        return null;
    }
}
