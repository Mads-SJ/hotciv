package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaCiv {
    private Game game;

    /** Fixture for betaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(BETA_CIV);
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

    @Test
    public void shouldBeYear3900BCAfterOneRound(){
        game.endOfTurn();
        game.endOfTurn();
        // -3900 = 3900BC
        assertThat(game.getAge(), is(-3900));
    }

    @Test
    public void shouldBeYear1BCAfter40Rounds(){
        for (int i = 0; i < 40; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(-1));
    }

    @Test
    public void shouldBeYear1ACAfter41Rounds(){
        for (int i = 0; i < 41; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1));
    }

    @Test
    public void shouldBeYear50ACAfter42Rounds(){
        for (int i = 0; i < 42; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(50));
    }

    @Test
    public void shouldBeYear100ACAfter43Rounds(){
        for (int i = 0; i < 43; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(100));
    }

    @Test
    public void shouldBeYear150ACAfter44Rounds(){
        for (int i = 0; i < 44; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(150));
    }

    @Test
    public void shouldBeYear1775ACAfter77Rounds(){
        for (int i = 0; i < 77; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1775));
    }

    @Test
    public void shouldBeYear1905ACAfter83Rounds(){
        for (int i = 0; i < 83; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1905));
    }

    @Test
    public void shouldBeYear1971ACAfter97Rounds(){
        for (int i = 0; i < 97; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getAge(), is(1971));
    }
}
