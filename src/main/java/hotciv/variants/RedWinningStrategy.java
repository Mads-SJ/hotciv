package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;

import static hotciv.framework.GameConstants.END_AGE;

public class RedWinningStrategy implements WinningStrategy {
    private final Game game;

    public RedWinningStrategy(Game game) {
        this.game = game;
    }
    @Override
    public Player checkIfGameOver() {
        if (game.getAge() == END_AGE) {
            return Player.RED;
        }
        return null;
    }

}
