package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.standard.TileImpl;

import static hotciv.framework.GameConstants.*;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public TileImpl[][] createWorldLayout() {
        // Set plains in the entire world. Update afterwards with specific tiles.
        TileImpl[][] worldGrid = new TileImpl[WORLDSIZE][WORLDSIZE];
        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {
                worldGrid[i][j] = new TileImpl(PLAINS);
            }
        }

        // Update world grid with correct tiles on their position.
        worldGrid[1][0] = new TileImpl(OCEANS);
        worldGrid[0][1] = new TileImpl(HILLS);
        worldGrid[2][2] = new TileImpl(MOUNTAINS);

        return worldGrid;
    }
}
