package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinningStrategy implements WinningStrategy {
    private BetaWinningStrategy betaWinningStrategy; // TODO skal man bruge interfacet eller konkret impl.
    private EpsilonWinningStrategy epsilonWinningStrategy;

    public ZetaWinningStrategy(BetaWinningStrategy betaWinningStrategy,
                               EpsilonWinningStrategy epsilonWinningStrategy) {
        this.betaWinningStrategy = betaWinningStrategy;
        this.epsilonWinningStrategy = epsilonWinningStrategy;
    }

    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (game.getCurrentRound() <= 20) return betaWinningStrategy.checkIfGameOver(game);
        return epsilonWinningStrategy.checkIfGameOver(game);
    }

    @Override
    public void incrementBattlesWonBy(GameImpl game, Player p) {
        if (game.getCurrentRound() > 20) {
            epsilonWinningStrategy.incrementBattlesWonBy(game, p);
        }
    }
}
