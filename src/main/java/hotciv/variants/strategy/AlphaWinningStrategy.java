package hotciv.variants.strategy;

import hotciv.common.strategy.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import static hotciv.framework.GameConstants.END_AGE;

public class AlphaWinningStrategy implements WinningStrategy {
    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (game.getAge() == END_AGE) {
            return Player.RED;
        }
        return null;
    }

    @Override
    public void incrementBattlesWonBy(GameImpl game, Player p) {
        // do nothing
    }

}
