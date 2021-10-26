package hotciv.standard;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.TileStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.DeltaFactory;
import hotciv.variants.factory.FractalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestIteration7 {
    private Game game;

    /** Fixture for iteration 7 testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void shouldNotBeTheSameTileAt0_0In25Games() {
        Tile[] tiles = new Tile[25];
        for (int i = 0; i < 25; i++) {
            Game game = new GameImpl(new FractalFactory());
            tiles[i] = game.getTileAt(new Position(0,0));
        }

        boolean flag = false;
        Tile firstTile = tiles[0];
        for (Tile t : tiles) {
            System.out.println(t.getTypeString());
            if (! firstTile.getTypeString().equals(t.getTypeString())) {
                flag = true;
                break;
            }
        }
        assertThat(flag, is(true));
    }
}