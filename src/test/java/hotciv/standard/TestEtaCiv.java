package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.EtaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaCiv {
    private Game game;
    private CityImpl city;

    /** Fixture for etaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new EtaFactory());
        city = (CityImpl) game.getCityAt(ALPHA_RED_CITY_POS);
    }

    @Test
    public void shouldBeIncreaseInCitySizeFrom1To2WhenFoodCollectedInCityExceeds8() {
        assertThat(city.getSize(), is(1));
        assertThat(city.getFood(), is(0));

        city.addFood(9);
        assertThat(city.getFood(), is(9));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(2));
    }

    @Test
    public void shouldBeIncreaseInCitySizeFrom4To5WhenFoodCollectedInCityExceeds17() {
        city.addFood(9);

        game.endOfTurn();
        game.endOfTurn();

        city.addFood(12);

        game.endOfTurn();
        game.endOfTurn();

        city.addFood(15);

        game.endOfTurn();
        game.endOfTurn();


        assertThat(city.getSize(), is(4));

        city.addFood(18);
        assertThat(city.getFood(), is(18));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(5));
    }
}
