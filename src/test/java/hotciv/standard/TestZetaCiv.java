package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.factory.ZetaFactory;
import hotciv.variants.factory.ZetaTestFactory;
import hotciv.variants.strategy.*;
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
        ZetaTestFactory zetaTestFactory = new ZetaTestFactory();
        game = new GameImpl(zetaTestFactory);
        ZetaWinningStrategy zetaWinningStrategy = (ZetaWinningStrategy) zetaTestFactory.createWinningStrategy();
        epsilonWinningStrategy = zetaWinningStrategy.getEpsilonWinningStrategy();
    }

    @Test
    public void shouldBeRedWinnerWhenRedOwnsAllCitiesAfter2Rounds() {
        Position redSettlerPos = new Position(4, 3);
        Position intermediatePos = new Position(4, 2);

        game.moveUnit(redSettlerPos, intermediatePos);

        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(intermediatePos, ALPHA_BLUE_CITY_POS);

        game.endOfTurn();
        game.endOfTurn();

        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(0));
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
        assertThat(game.getCityAt(ALPHA_RED_CITY_POS).getOwner(), is(Player.RED));

        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(3));
        assertThat(game.getWinner(), is(Player.BLUE));
    }
}
