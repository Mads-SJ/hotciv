package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.GammaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestThetaCiv {
    private Game game;

    /** Fixture for thetaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void shouldBe0ResourcesForDesertTile() {
        assertThat(new TileImpl(DESERT).getResources(), is(0));
    }

    @Test
    public void shouldBeCostOf30ForSandworm() {
        CityImpl city = new CityImpl(Player.RED);
        city.setProduction(SANDWORM);
        assertThat(city.getCostOfNewUnit(), is(30));
    }

    @Test
    public void shouldBeMoveCountOf2ForSandworm() {
        assertThat(new UnitImpl(Player.RED, SANDWORM).getMoveCount(), is(2));
    }
}
