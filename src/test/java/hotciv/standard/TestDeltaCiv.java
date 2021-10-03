package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaCiv {
    private Game game;

    /** Fixture for deltaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaWinningStrategy(), new AlphaAgingStrategy(), new AlphaActionStrategy(),
                new DeltaWorldLayoutStrategy(), new AlphaAttackingStrategy());
    }

    @Test
    public void shouldBeOceansAt0_0() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(OCEANS));
    }

    @Test
    public void shouldBeOceansAt15_15() {
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is(OCEANS));
    }

    @Test
    public void shouldBeHillsAt1_3() {
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is(HILLS));
    }

    @Test
    public void shouldBeHillsAt14_6() {
        assertThat(game.getTileAt(new Position(14,6)).getTypeString(), is(HILLS));
    }

    @Test
    public void shouldBeMountainsAt0_5() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void shouldBeMountainsAt11_5() {
        assertThat(game.getTileAt(new Position(11,5)).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void shouldBeForestAt1_9() {
        assertThat(game.getTileAt(new Position(1,9)).getTypeString(), is(FOREST));
    }

    @Test
    public void shouldBeForestAt12_8() {
        assertThat(game.getTileAt(new Position(12,8)).getTypeString(), is(FOREST));
    }

    @Test
    public void shouldBePlainsAt0_4() {
        assertThat(game.getTileAt(new Position(0,4)).getTypeString(), is(PLAINS));
    }

    @Test
    public void shouldBePlainsAt15_13() {
        assertThat(game.getTileAt(new Position(15,13)).getTypeString(), is(PLAINS));
    }

    @Test
    public void shouldBeRedCityAt8_12() {
        assertThat(game.getCityAt(DELTA_RED_CITY_POS).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueCityAt4_5() {
        assertThat(game.getCityAt(DELTA_BLUE_CITY_POS).getOwner(), is(Player.BLUE));
    }
}
