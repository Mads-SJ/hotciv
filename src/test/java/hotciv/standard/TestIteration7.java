package hotciv.standard;

import hotciv.common.factory.GameFactory;
import hotciv.common.strategy.TileStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.variants.decorator.GameTranscriber;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.DeltaFactory;
import hotciv.variants.factory.FractalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static hotciv.framework.GameConstants.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestIteration7 {
    private Game game;
    private GameTranscriber gameTranscriber;
    private ByteArrayOutputStream baos;

    /** Fixture for iteration 7 testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
        gameTranscriber = new GameTranscriber(game);
        baos = gameTranscriber.getBaos();
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
            if (! firstTile.getTypeString().equals(t.getTypeString())) {
                flag = true;
                break;
            }
        }
        assertThat(flag, is(true));
    }

  /*  @Test
    public void shouldPrintRED_ends_turn() {
        gameTranscriber.endOfTurn();

        String output = baos.toString();

        assertThat(output, is("RED ends turn.\n"));
    }

    @Test
    public void shouldPrintRedMovesArcherFrom2_0To2_1() {
        Position redArcherPos = new Position(2,0);
        Position endPos = new Position(2,1);

        gameTranscriber.moveUnit(redArcherPos, endPos);

        String output = baos.toString();

        assertThat(output, is("RED moves archer from [2,0] to [2,1].\n"));
    }

    @Test
    public void shouldPrintREDChangesWorkForceFocusInCityAt1_1ToFoodFocus() {
        gameTranscriber.changeWorkForceFocusInCityAt(ALPHA_RED_CITY_POS, FOOD);

        String output = baos.toString();

        assertThat(output, is("RED changes work force focus in city at [1,1] to food focus.\n"));
    }

    @Test
    public void shouldPrintBLUEChangesProductionInCityAt4_1ToLegion() {
        game.endOfTurn();
        gameTranscriber.changeProductionInCityAt(ALPHA_BLUE_CITY_POS, LEGION);

        String output = baos.toString();

        assertThat(output, is("BLUE changes production in city at [4,1] to legion.\n"));
    }

    @Test
    public void shouldPrintREDPerformsUnitActionAt4_3ForSettler() {
        Position redSettlerPos = new Position(4,3);

        gameTranscriber.performUnitActionAt(redSettlerPos);

        String output = baos.toString();

        assertThat(output, is("RED performs unit action at [4,3] for settler.\n"));
    }

    @Test
    public void shouldPrintForMultipleActions() {
        Position redArcherPos = new Position(2,0);
        Position endPos = new Position(2,1);
        Position redSettlerPos = new Position(4,3);

        gameTranscriber.moveUnit(redArcherPos, endPos);
        gameTranscriber.changeWorkForceFocusInCityAt(ALPHA_RED_CITY_POS, FOOD);
        gameTranscriber.performUnitActionAt(redSettlerPos);
        gameTranscriber.endOfTurn();
        gameTranscriber.changeProductionInCityAt(ALPHA_BLUE_CITY_POS, LEGION);

        String[] expectedOutput = {
                "RED moves archer from [2,0] to [2,1].",
                "RED changes work force focus in city at [1,1] to food focus.",
                "RED performs unit action at [4,3] for settler.",
                "RED ends turn.",
                "BLUE changes production in city at [4,1] to legion."
        };

        String[] output = baos.toString().split("\n");

        assertThat(output, is(expectedOutput));

    }*/

}