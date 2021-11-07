package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.stub.GameObserverSpy;
import hotciv.variants.decorator.GameTranscriber;
import hotciv.variants.factory.AlphaFactory;
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
        game = new GameImpl(new AlphaFactory());
        spy = new GameObserverSpy();

        game.addObserver(spy);
    }

    @Test
    public void shouldBeLastMethodCalledWorldChangedAtForSpyAfterUnitMoves() {
        Position redSettlerPos = new Position(4,3);
        Position candidatePos = new Position(5,4);
        game.moveUnit(redSettlerPos, candidatePos);

        assertThat(spy.lastMethodCalled(), is("worldChangedAt"));
    }
}
