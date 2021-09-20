package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaCiv {
    private Game game;

    /** Fixture for gammaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(GAMMA_CIV);
    }

    @Test
    public void shouldBeRemovedFromWorldWhenSettlerPerformsAction() {
        Position settlerPosition = new Position(4,3);
        // Perform unit action for settler at position 4,3
        game.performUnitActionAt(settlerPosition);

        assertThat(game.getUnitAt(settlerPosition), nullValue());
    }

    @Test
    public void shouldBeCityAtSettlerPositionAfterSettlerPerformsAction() {
        Position settlerPosition = new Position(4,3);
        assertThat(game.getCityAt(settlerPosition), is(nullValue()));

        game.performUnitActionAt(settlerPosition);

        assertThat(game.getCityAt(settlerPosition).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBePopulationSize1ForCityAfterBeingCreatedFromSettler() {
        Position settlerPosition = new Position(4,3);

        game.performUnitActionAt(settlerPosition);

        assertThat(game.getCityAt(settlerPosition).getSize(), is(1));
    }
}
