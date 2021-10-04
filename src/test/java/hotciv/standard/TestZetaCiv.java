package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaCiv {
    private Game game;
    private EpsilonWinningStrategy epsilonWinningStrategy;

    /** Fixture for zetaciv testing. */
    @BeforeEach
    public void setUp() {
        epsilonWinningStrategy = new EpsilonWinningStrategy();
        game = new GameImpl(new ZetaWinningStrategy(new BetaWinningStrategy(), epsilonWinningStrategy), new AlphaAgingStrategy(), new AlphaActionStrategy(),
                new AlphaWorldLayoutStrategy(), new AlphaAttackingStrategy());
    }

    @Test
    public void shouldBeRedWinnerWhenRedOwnsAllCitiesAfter2Rounds() {
        // TODO: eventuelt tilføj asserts der viser antal vundne battles.
        Position redSettlerPos = new Position(4, 3);
        Position intermediatePos = new Position(4, 2);

        game.moveUnit(redSettlerPos, intermediatePos);

        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(intermediatePos, ALPHA_BLUE_CITY_POS);

        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueWinnerAfter23RoundsWhenBlueHasWon3AttacksCountingFrom20thRound() {
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        Position blueLegionPos = new Position(3,2);
        Position firstRedSettlerPos = new Position(4,2);
        Position secondRedSettlerPos = new Position(5,2);
        Position thirdRedSettlerPos = new Position(6,2);

        GameImpl gameImpl = (GameImpl) game;
        gameImpl.setUnitAt(firstRedSettlerPos, new UnitImpl(Player.RED, SETTLER));
        gameImpl.setUnitAt(secondRedSettlerPos, new UnitImpl(Player.RED, SETTLER));
        gameImpl.setUnitAt(thirdRedSettlerPos, new UnitImpl(Player.RED, SETTLER));

        game.endOfTurn();

        game.moveUnit(blueLegionPos, firstRedSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(firstRedSettlerPos, secondRedSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(secondRedSettlerPos, thirdRedSettlerPos);
        game.endOfTurn();

        // Not all cities are owned by blue.
        // TODO: eventuelt assert at røds by er ejet af rød
        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(3));
        assertThat(game.getWinner(), is(Player.BLUE));
    }
}
