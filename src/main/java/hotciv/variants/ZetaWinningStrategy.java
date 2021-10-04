package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinningStrategy implements WinningStrategy {
    private BetaWinningStrategy betaWinningStrategy; // TODO skal man bruge interfacet eller konkret impl.

    public ZetaWinningStrategy(BetaWinningStrategy betaWinningStrategy) {
        this.betaWinningStrategy = betaWinningStrategy;
    }

    @Override
    public Player checkIfGameOver(GameImpl game) {
        return betaWinningStrategy.checkIfGameOver(game);
    }

    @Override
    public void incrementBattlesWonBy(Player p) {
        // do nothing for now
    }
}
