package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.END_AGE;

public class RedWinningStrategy implements WinningStrategy {
    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (game.getAge() == END_AGE) {
            return Player.RED;
        }
        return null;
    }

}
