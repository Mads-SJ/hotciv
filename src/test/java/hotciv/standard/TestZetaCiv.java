package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestZetaCiv {
    private Game game;

    /** Fixture for zetaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new ZetaWinningStrategy(new BetaWinningStrategy()), new AlphaAgingStrategy(), new AlphaActionStrategy(),
                new AlphaWorldLayoutStrategy(), new AlphaAttackingStrategy());
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

        assertThat(game.getWinner(), is(Player.RED));
    }
}
