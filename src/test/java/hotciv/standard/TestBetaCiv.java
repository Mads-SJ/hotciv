package hotciv.standard;

import hotciv.common.AgingStrategy;
import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBetaCiv {
    private Game game;
    private AgingStrategy agingStrategy;

    /** Fixture for betaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new BetaWinningStrategy(), new BetaAgingStrategy(), new AlphaActionStrategy(),
                new AlphaWorldLayoutStrategy());
        agingStrategy = new BetaAgingStrategy();
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
    public void shouldBeNoWinnerAtStartOfGame() {
        assertThat(game.getWinner(), is(nullValue()));
    }

    @Test
    public void shouldBeYear3900BCAfter4000BC(){
        assertThat(agingStrategy.ageWorld(START_AGE), is(-3900));
    }

    @Test
    public void shouldBeYear1BCAfter100BC(){
        assertThat(agingStrategy.ageWorld(-100), is(-1));
    }

    @Test
    public void shouldBeYear1ACAfter1BC(){
        assertThat(agingStrategy.ageWorld(-1), is(1));
    }

    @Test
    public void shouldBeYear50ACAfterYear1AC(){
        assertThat(agingStrategy.ageWorld(1), is(50));
    }








    @Test
    public void shouldBeYear100ACAfter50AC(){
        assertThat(agingStrategy.ageWorld(50), is(100));
    }

    @Test
    public void shouldBeYear150ACAfter100AC(){
        assertThat(agingStrategy.ageWorld(100), is(150));
    }

    @Test
    public void shouldBeYear1775ACAfter1750(){
        assertThat(agingStrategy.ageWorld(1750), is(1775));
    }


    @Test
    public void shouldBeYear1905ACAfter1900AC(){
        assertThat(agingStrategy.ageWorld(1900), is(1905));
    }

    @Test
    public void shouldBeYear1971ACAfter1970AC(){
        assertThat(agingStrategy.ageWorld(1970), is(1971));
    }
}
