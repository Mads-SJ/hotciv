package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.jupiter.api.*;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGammaCiv {
    private Game game;
    private Position archerPos;
    private Position settlerPos;

    /** Fixture for gammaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(GAMMA_CIV);
        settlerPos = new Position(4,3);
        archerPos = new Position(2,0);
    }

    @Test
    public void shouldBeRemovedFromWorldWhenSettlerPerformsAction() {
        // Perform unit action for settler at position 4,3
        game.performUnitActionAt(settlerPos);

        assertThat(game.getUnitAt(settlerPos), nullValue());
    }

    @Test
    public void shouldBeCityAtSettlerPositionAfterSettlerPerformsAction() {
        assertThat(game.getCityAt(settlerPos), is(nullValue()));

        game.performUnitActionAt(settlerPos);

        assertThat(game.getCityAt(settlerPos).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBePopulationSize1ForCityAfterBeingCreatedFromSettler() {
        game.performUnitActionAt(settlerPos);

        assertThat(game.getCityAt(settlerPos).getSize(), is(1));
    }

    @Test
    public void shouldBeDoubleDefensiveStrengthForArcherAfterFortify() {
        Unit archer = game.getUnitAt(archerPos);
        assertThat(archer.getDefensiveStrength(), is(3));

        game.performUnitActionAt(archerPos);
        assertThat(archer.getDefensiveStrength(), is(3*2));
    }

    @Test
    public void shouldNotBeAbleToMoveArcherWhenFortified() {
        Position candidatePos = new Position(2, 1);
        game.performUnitActionAt(archerPos);

        Boolean hasBeenMoved = game.moveUnit(archerPos, candidatePos);
        assertThat(hasBeenMoved, is(false));
    }

    @Test
    public void shouldBeAbleToMoveAfterRemovalOfFortification() {
        Position candidatePos = new Position(2, 1);
        game.performUnitActionAt(archerPos);
        game.performUnitActionAt(archerPos);

        Boolean hasBeenMoved = game.moveUnit(archerPos, candidatePos);
        assertThat(hasBeenMoved, is(true));
    }

    @Test
    public void shouldBeNormalDefensiveStrengthForArcherAfterRemovalOfFortification() {
        Unit archer = game.getUnitAt(archerPos);
        game.performUnitActionAt(archerPos);
        game.performUnitActionAt(archerPos);
        assertThat(archer.getDefensiveStrength(), is(3));
    }
}
