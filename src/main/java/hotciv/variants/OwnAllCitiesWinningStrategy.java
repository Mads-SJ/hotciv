package hotciv.variants;

import hotciv.common.WinningStrategy;
import hotciv.framework.*;
import hotciv.standard.GameImpl;

public class OwnAllCitiesWinningStrategy implements WinningStrategy {
    private final GameImpl game;

    public OwnAllCitiesWinningStrategy(GameImpl game) {
        this.game = game;
    }
    @Override
    public Player checkIfGameOver() {
        City seen = null;

        for (Position p: game.getCities().keySet()) {
            City c = game.getCityAt(p);

            if (seen == null) {
                seen = c;
            }
            else if (seen.getOwner() != c.getOwner()) {
                return null;
            }
        }
        // Assuming that a city always exists because of this winning strategy
        return seen.getOwner(); // TODO: check null value?
    }

}
