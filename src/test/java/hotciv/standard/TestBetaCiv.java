package hotciv.standard;

import hotciv.framework.*;

import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaCiv {
    private Game game;

    /** Fixture for betaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldBeBlueWinningWhenBlueOwnsAllCities() {
        CityImpl redCity = (CityImpl) game.getCityAt(GameImpl.RED_CITY_POSITION);
        redCity.setOwner(Player.BLUE);

        // Winner is only updated at end of each round
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedWinningWhenRedOwnsAllCities() {
        CityImpl blueCity = (CityImpl) game.getCityAt(GameImpl.BLUE_CITY_POSITION);
        blueCity.setOwner(Player.RED);

        // Winner is only updated at end of each round
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.RED));
    }
}
