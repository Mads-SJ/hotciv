package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.GammaFactory;
import hotciv.variants.factory.ThetaFactory;
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
        game = new GameImpl(new ThetaFactory());
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

    @Test
    public void shouldBe10DefensiveStrengthForSandworm() {
        assertThat(new UnitImpl(Player.RED, SANDWORM).getDefensiveStrength(), is(10));
    }

    @Test
    public void shouldBe0AttackingStrengthForSandworm() {
        assertThat(new UnitImpl(Player.RED, SANDWORM).getAttackingStrength(), is(0));
    }

    @Test
    public void shouldBePlainsAt0_3() {
        assertThat(game.getTileAt(new Position(0,3)).getTypeString(), is(PLAINS));
    }

    @Test
    public void shouldBeOceansAt0_0() {
        assertThat(game.getTileAt(new Position(0,0)).getTypeString(), is(OCEANS));
    }

    @Test
    public void shouldBeHillsAt1_3() {
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is(HILLS));
    }

    @Test
    public void shouldBeForestAt12_9() {
        assertThat(game.getTileAt(new Position(12,9)).getTypeString(), is(FOREST));
    }

    @Test
    public void shouldBeMountainAt14_10() {
        assertThat(game.getTileAt(new Position(14,10)).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void shouldBeDesertAt0_5() {
        assertThat(game.getTileAt(new Position(0,5)).getTypeString(), is(DESERT));
    }

    @Test
    public void shouldBeDesertAt15_11() {
        assertThat(game.getTileAt(new Position(15,11)).getTypeString(), is(DESERT));
    }

    @Test
    public void shouldBeValidMoveForSandwormWhenMovingToDesertTileWhenPlacedOnDesertTile() {
        GameImpl gameImpl = (GameImpl) game;
        Position sandwormPos = new Position(15, 7);
        Position candidatePos = new Position(15, 8);

        assertThat(game.getTileAt(sandwormPos).getTypeString(), is(DESERT));
        assertThat(game.getTileAt(candidatePos).getTypeString(), is(DESERT));

        gameImpl.setUnitAt(sandwormPos, new UnitImpl(Player.RED, SANDWORM));
        gameImpl.moveUnit(sandwormPos, candidatePos);

        assertThat(game.getUnitAt(candidatePos).getTypeString(), is(SANDWORM));
    }

    @Test
    public void shouldNotBeValidMoveForSandwormWhenMovingToPlainsTileWhenPlacedOnDesertTile() {
        GameImpl gameImpl = (GameImpl) game;
        Position sandwormPos = new Position(15, 6);
        Position candidatePos = new Position(15, 5);

        assertThat(game.getTileAt(sandwormPos).getTypeString(), is(DESERT));
        assertThat(game.getTileAt(candidatePos).getTypeString(), is(PLAINS));

        gameImpl.setUnitAt(sandwormPos, new UnitImpl(Player.RED, SANDWORM));
        gameImpl.moveUnit(sandwormPos, candidatePos);

        assertThat(game.getUnitAt(sandwormPos).getTypeString(), is(SANDWORM));
        assertThat(game.getUnitAt(candidatePos), is(nullValue()));
    }

    @Test
    public void shouldBeAbleToMoveTwiceInATurnForSandworm() {
        GameImpl gameImpl = (GameImpl) game;
        Position sandwormPos = new Position(15, 6);
        Position intermediatePos = new Position(15, 7);
        Position finalPos = new Position(15, 8);

        assertThat(game.getTileAt(intermediatePos).getTypeString(), is(DESERT));
        assertThat(game.getTileAt(finalPos).getTypeString(), is(DESERT));

        gameImpl.setUnitAt(sandwormPos, new UnitImpl(Player.RED, SANDWORM));
        gameImpl.moveUnit(sandwormPos, intermediatePos);
        gameImpl.moveUnit(intermediatePos, finalPos);

        assertThat(game.getUnitAt(finalPos).getTypeString(), is(SANDWORM));
    }

    @Test
    public void shouldPlaceSandwormOnRedCityPositionWhenAffordable() {
        assertThat(game.getTileAt(DELTA_RED_CITY_POS).getTypeString(), is(DESERT));

        game.changeProductionInCityAt(DELTA_RED_CITY_POS, SANDWORM);

        for (int i = 0; i < 5; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(DELTA_RED_CITY_POS).getTypeString(), is(SANDWORM));
    }

    @Test
    public void shouldPlaceSandwormEastOfRedCityPositionWhenAffordable() {
        Position finalPosition = new Position(8, 13);

        assertThat(game.getTileAt(finalPosition).getTypeString(), is(DESERT));

        game.changeProductionInCityAt(DELTA_RED_CITY_POS, SANDWORM);

        for (int i = 0; i < 10; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }

        assertThat(game.getUnitAt(finalPosition).getTypeString(), is(SANDWORM));
    }
}
