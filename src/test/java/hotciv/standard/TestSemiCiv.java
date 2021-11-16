package hotciv.standard;

import hotciv.common.strategy.UnitStrategy;
import hotciv.framework.*;
import hotciv.variants.factory.SemiTestFactory;
import hotciv.variants.strategy.AlphaUnitStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hotciv.framework.GameConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSemiCiv {
    private Game game;

    /** Fixture for semiciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new SemiTestFactory());
    }

    @Test
    public void shouldBeLostBattleForBlueLegionWhenAttackingRedArcherThatIsFortified() {
        Position redArcherPos = new Position(0,3);
        Position blueLegionPos = new Position(0,4);
        GameImpl gameImpl = (GameImpl) game;

        UnitStrategy unitStrategy = new AlphaUnitStrategy();
        UnitImpl redArcher = new UnitImpl(Player.RED, ARCHER, unitStrategy);
        UnitImpl blueLegion = new UnitImpl(Player.BLUE, LEGION, unitStrategy);

        gameImpl.setUnitAt(redArcherPos, redArcher);
        gameImpl.setUnitAt(blueLegionPos, blueLegion);

        game.performUnitActionAt(redArcherPos);

        game.endOfTurn();

        assertThat(redArcher.getDefensiveStrength(), is(3*2));
        assertThat(blueLegion.getAttackingStrength(), is(4));

        // fixed die roll sets both eyes to 1
        game.moveUnit(blueLegionPos, redArcherPos);

        assertThat(game.getUnitAt(redArcherPos).getTypeString(), is(ARCHER));
        assertThat(game.getUnitAt(blueLegionPos), is(nullValue()));
    }
}
