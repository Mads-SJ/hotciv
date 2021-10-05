package hotciv.variants.strategy;

import hotciv.common.strategy.WinningStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinningStrategy implements WinningStrategy {
    private BetaWinningStrategy betaWinningStrategy;
    private EpsilonWinningStrategy epsilonWinningStrategy;
    private WinningStrategy currentState;

    public ZetaWinningStrategy() {
        this.betaWinningStrategy = new BetaWinningStrategy();
        this.epsilonWinningStrategy = new EpsilonWinningStrategy();
    }

    @Override
    public Player checkIfGameOver(GameImpl game) {
        if (game.getCurrentRound() < 20) currentState = betaWinningStrategy;
        else currentState = epsilonWinningStrategy;

        return currentState.checkIfGameOver(game);
    }

    @Override
    public void incrementBattlesWonBy(GameImpl game, Player p) {
        currentState.incrementBattlesWonBy(game, p);
    }

    public EpsilonWinningStrategy getEpsilonWinningStrategy() {
        return epsilonWinningStrategy;
    }
}
