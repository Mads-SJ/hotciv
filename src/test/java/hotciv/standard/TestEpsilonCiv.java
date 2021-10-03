package hotciv.standard;

import hotciv.framework.Game;
import hotciv.variants.*;
import org.junit.jupiter.api.BeforeEach;

public class TestEpsilonCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @BeforeEach
    public void setUp() {
        game = new GameImpl(new AlphaWinningStrategy(), new AlphaAgingStrategy(), new AlphaActionStrategy(),
                new AlphaWorldLayoutStrategy(), new EpsilonAttackingStrategy());
    }
}
