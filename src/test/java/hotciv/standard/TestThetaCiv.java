package hotciv.standard;

import hotciv.framework.Game;
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
}
