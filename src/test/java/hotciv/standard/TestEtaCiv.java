package hotciv.standard;

import hotciv.framework.Game;
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

        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(5));
    }

    @Test
    public void shouldResetFoodOfCityAfterIncreaseInPopulation() {
        assertThat(city.getSize(), is(1));
        assertThat(city.getFood(), is(0));

        city.addFood(9);
        assertThat(city.getFood(), is(9));

        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(2));
        assertThat(city.getFood(), is(0));
    }

    @Test
    public void shouldNotIncreasePopulationForCityWithSize9() {
        for (int i = 0; i < 8; i++) {
            city.addFood(100);
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(city.getSize(), is(9));

        city.addFood(100);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(city.getSize(), is(9));
    }

    @Test
    public void shouldBeIncreaseOf1ProductionAnd1FoodForBlueCityWhenWorkforceFocusIsProductionAfterRoundOne() {
        CityImpl blueCity = (CityImpl) game.getCityAt(ALPHA_BLUE_CITY_POS);

        blueCity.setWorkforceFocus(productionFocus);

        game.endOfTurn();
        game.endOfTurn();

        assertThat(blueCity.getTreasury(), is(1));
        assertThat(blueCity.getFood(), is(1));
    }

    @Test
    public void shouldBe4ProductionAnd10FoodForBlueCityWhenWorkforceFocusIsFoodAndPopulationIs4AfterRound5() {
        CityImpl blueCity = (CityImpl) game.getCityAt(ALPHA_BLUE_CITY_POS);

        blueCity.setWorkforceFocus(foodFocus);

        for (int i = 0; i < 3; i++) {
            blueCity.addFood(100);
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(blueCity.getSize(), is(4));

        game.endOfTurn();
        game.endOfTurn();

        // each city gets 1 production each round if workforce focus is set to food
        assertThat(blueCity.getTreasury(), is( 1 + 1 + 1 + 1));

        // Food gets reset when increasing population which is done each round
        assertThat(blueCity.getFood(), is(10));
    }

    @Test
    public void shouldBe2ProductionAnd1FoodForRedCityWhenWorkforceFocusIsProductionAndPopulationIs4AfterRound5() {
        CityImpl redCity = (CityImpl) game.getCityAt(ALPHA_RED_CITY_POS);

        redCity.setWorkforceFocus(productionFocus);

        for (int i = 0; i < 3; i++) {
            redCity.addFood(100);
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(redCity.getSize(), is(4));

        game.endOfTurn();
        game.endOfTurn();


        // City buys archer after round 5, hence the -10
        assertThat(redCity.getTreasury(), is((1 + 3 + 4 + 4) - 10));

        // Food gets reset when increasing population which is done each round
        assertThat(redCity.getFood(), is(1));
    }
}
