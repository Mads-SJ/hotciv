package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new EpsilonWinningStrategy(), new AlphaAgingStrategy(), new AlphaActionStrategy(),
                new AlphaWorldLayoutStrategy(), new EpsilonAttackingStrategy());
    }

    @Test
    public void shouldBeRedWinningAfter3SuccessfulAttacks() {
        Position redArcherPos = new Position(2,0);
        Position firstBlueSettlerPos = new Position(3,0);
        Position secondBlueSettlerPos = new Position(4,0);
        Position thirdBlueSettlerPos = new Position(5,0);

        GameImpl gameImpl = (GameImpl) game;
        gameImpl.setUnitAt(firstBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));
        gameImpl.setUnitAt(secondBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));
        gameImpl.setUnitAt(thirdBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));

        game.moveUnit(redArcherPos, firstBlueSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(firstBlueSettlerPos, secondBlueSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(secondBlueSettlerPos, thirdBlueSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueWinningAfter3SuccessfulAttacks() {
        Position blueLegionPos = new Position(3,2);
        Position firstRedSettlerPos = new Position(4,2);
        Position secondRedSettlerPos = new Position(5,2);
        Position thirdRedSettlerPos = new Position(6,2);

        GameImpl gameImpl = (GameImpl) game;
        gameImpl.setUnitAt(firstRedSettlerPos, new UnitImpl(Player.RED, SETTLER));
        gameImpl.setUnitAt(secondRedSettlerPos, new UnitImpl(Player.RED, SETTLER));
        gameImpl.setUnitAt(thirdRedSettlerPos, new UnitImpl(Player.RED, SETTLER));

        game.endOfTurn();

        game.moveUnit(blueLegionPos, firstRedSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(firstRedSettlerPos, secondRedSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        game.moveUnit(secondRedSettlerPos, thirdRedSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeNoWinnerAfter1SuccessfulAttack() {
        Position redArcherPos = new Position(2,0);
        Position firstBlueSettlerPos = new Position(3,0);
        Position secondBlueSettlerPos = new Position(4,0);
        Position thirdBlueSettlerPos = new Position(5,0);

        GameImpl gameImpl = (GameImpl) game;
        gameImpl.setUnitAt(firstBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));
        gameImpl.setUnitAt(secondBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));
        gameImpl.setUnitAt(thirdBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER));

        game.moveUnit(redArcherPos, firstBlueSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(nullValue()));
    }
}
