package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.stub.GameObserverSpy;
import hotciv.variants.decorator.GameTranscriber;
import hotciv.variants.factory.AlphaFactory;
import hotciv.variants.factory.GammaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestIteration8 {
    Game game;
    GameObserverSpy spy;

    /** Fixture for iteration 8 testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new GammaFactory());
        spy = new GameObserverSpy();

        game.addObserver(spy);
    }

    @Test
    public void shouldBeLastMethodCalledWorldChangedAtForSpyAfterSettlerMoves() {
        assertThat(spy.lastMethodCalled(), is("none"));

        Position redSettlerPos = new Position(4,3);
        Position candidatePos = new Position(5,4);
        game.moveUnit(redSettlerPos, candidatePos);

        assertThat(spy.lastMethodCalled(), is("worldChangedAt"));
    }

    @Test
    public void shouldBeLastMethodCalledWorldChangedAtForSpyAfterSettlerPerformsAction() {
        assertThat(spy.lastMethodCalled(), is("none"));

        Position redSettlerPos = new Position(4,3);

        game.performUnitActionAt(redSettlerPos);

        assertThat(spy.lastMethodCalled(), is("worldChangedAt"));
    }
}
