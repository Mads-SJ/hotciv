package hotciv.standard;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.UnitStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.factory.EpsilonTestFactory;
import hotciv.variants.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEpsilonCiv {
    private Game game;
    private FixedDecisionStrategy decisionStrategy;
    private EpsilonWinningStrategy epsilonWinningStrategy;
    private UnitStrategy unitStrategy;

    /** Fixture for epislonciv testing. */
    @BeforeEach
    public void setUp() {
        EpsilonTestFactory epsilonTestFactory = new EpsilonTestFactory();
        game = new GameImpl(epsilonTestFactory);
        epsilonWinningStrategy = (EpsilonWinningStrategy) epsilonTestFactory.createWinningStrategy();
        EpsilonAttackingStrategy attackingStrategy = (EpsilonAttackingStrategy) epsilonTestFactory.createAttackingStrategy();
        decisionStrategy = (FixedDecisionStrategy) attackingStrategy.getDecisionStrategy();
        unitStrategy = epsilonTestFactory.createUnitStrategy();
    }

    @Test
    public void shouldBeSuccessfulAttackWhenBlueLegionAttacksRedSettler() {
        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(0));
        Position blueLegionPos = new Position(3,2);
        Position redSettlerPos = new Position(4,3);

        game.endOfTurn();
        game.moveUnit(blueLegionPos, redSettlerPos);

        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(1));
    }

    @Test
    public void shouldBeLossForRedSettlerAgainstBlueLegion() {
        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(0));
        Position blueLegionPos = new Position(3,2);
        Position redSettlerPos = new Position(4,3);

        game.moveUnit(redSettlerPos, blueLegionPos);

        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(0));
    }

    @Test
    public void shouldBeWinForRedSettlerOverBlueLegionWhenThereAreThreeSupportingUnitsAroundIt() {
        GameImpl gameImpl = (GameImpl) game;
        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(0));
        Position blueLegionPos = new Position(3,2);
        Position redSettlerPos = new Position(4,3);

        // Add red units around red settler
        gameImpl.setUnitAt(new Position(4,4), new UnitImpl(Player.RED, ARCHER, unitStrategy));
        gameImpl.setUnitAt(new Position(4,2), new UnitImpl(Player.RED, ARCHER, unitStrategy));
        gameImpl.setUnitAt(new Position(3,3), new UnitImpl(Player.RED, ARCHER, unitStrategy));

        game.moveUnit(redSettlerPos, blueLegionPos);

        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(1));
    }

    @Test
    public void shouldBeLossForBlueLegionAgainstRedSettlerWhenRedSettlerIsOnAHill() {
        GameImpl gameImpl = (GameImpl) game;
        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(0));

        Position blueLegionPos = new Position(0, 2);
        gameImpl.setUnitAt(blueLegionPos, new UnitImpl(Player.BLUE, LEGION, unitStrategy));

        Position hillPosition = new Position(0, 1);
        gameImpl.setUnitAt(hillPosition, new UnitImpl(Player.RED, SETTLER, unitStrategy));

        game.endOfTurn(); // blue's turn to move
        game.moveUnit(blueLegionPos, hillPosition);

        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(0));
    }

    @Test
    public void shouldBeWinForBlueLegionAgainstRedSettlerWhenRedSettlerIsOnAHillButBlueHasDieRoll2AndRed1() {
        GameImpl gameImpl = (GameImpl) game;
        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(0));

        Position blueLegionPos = new Position(0, 2);
        gameImpl.setUnitAt(blueLegionPos, new UnitImpl(Player.BLUE, LEGION, unitStrategy));

        Position hillPosition = new Position(0, 1);
        gameImpl.setUnitAt(hillPosition, new UnitImpl(Player.RED, SETTLER, unitStrategy));

        decisionStrategy.setAttackingEyes(2);

        game.endOfTurn(); // blue's turn to move
        game.moveUnit(blueLegionPos, hillPosition);

        assertThat(epsilonWinningStrategy.getBlueAttackingWins(), is(1));
    }

    @Test
    public void shouldRemoveRedSettlerWhenLosingEngagingBattleToBlueLegion() {
        Position blueLegionPos = new Position(3,2);
        Position redSettlerPos = new Position(4,3);

        decisionStrategy.setDefendingEyes(6);

        game.moveUnit(redSettlerPos, blueLegionPos);

        assertThat(epsilonWinningStrategy.getRedAttackingWins(), is(0));
        assertThat(game.getUnitAt(redSettlerPos), is(nullValue()));
        assertThat(game.getUnitAt(blueLegionPos).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedWinningAfter3SuccessfulAttacks() {
        Position redArcherPos = new Position(2,0);
        Position firstBlueSettlerPos = new Position(3,0);
        Position secondBlueSettlerPos = new Position(4,0);
        Position thirdBlueSettlerPos = new Position(5,0);

        GameImpl gameImpl = (GameImpl) game;
        gameImpl.setUnitAt(firstBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));
        gameImpl.setUnitAt(secondBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));
        gameImpl.setUnitAt(thirdBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));

        decisionStrategy.setAttackingEyes(6);

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
        gameImpl.setUnitAt(firstRedSettlerPos, new UnitImpl(Player.RED, SETTLER, unitStrategy));
        gameImpl.setUnitAt(secondRedSettlerPos, new UnitImpl(Player.RED, SETTLER, unitStrategy));
        gameImpl.setUnitAt(thirdRedSettlerPos, new UnitImpl(Player.RED, SETTLER, unitStrategy));

        decisionStrategy.setAttackingEyes(6);

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
        gameImpl.setUnitAt(firstBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));
        gameImpl.setUnitAt(secondBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));
        gameImpl.setUnitAt(thirdBlueSettlerPos, new UnitImpl(Player.BLUE, SETTLER, unitStrategy));

        decisionStrategy.setAttackingEyes(6);

        game.moveUnit(redArcherPos, firstBlueSettlerPos);
        game.endOfTurn();
        game.endOfTurn();

        assertThat(game.getWinner(), is(nullValue()));
    }
}
