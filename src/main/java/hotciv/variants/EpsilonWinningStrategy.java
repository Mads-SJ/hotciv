package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class EpsilonWinningStrategy implements WinningStrategy {
    private int redAttackingWins;
    private int blueAttackingWins;

    public EpsilonWinningStrategy() {
        redAttackingWins = 0;
        blueAttackingWins = 0;
    }

    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (redAttackingWins == 3) return Player.RED;
        if (blueAttackingWins == 3) return Player.BLUE;
        return null;
    }

    @Override
    public void incrementBattlesWonBy(Player p) {
        if (p == Player.RED) redAttackingWins++;
        else blueAttackingWins++;
    }
}
