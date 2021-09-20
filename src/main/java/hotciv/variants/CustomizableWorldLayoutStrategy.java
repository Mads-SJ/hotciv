package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import static hotciv.framework.GameConstants.*;
import hotciv.standard.TileImpl;

public class CustomizableWorldLayoutStrategy implements WorldLayoutStrategy {
    private String[] layout;

    public CustomizableWorldLayoutStrategy(String[] layout) {
        this.layout = layout;
    }
    @Override
    public TileImpl[][] createWorldLayout() {
        TileImpl[][] theWorld = new TileImpl[WORLDSIZE][WORLDSIZE];
        String line;
        for (int r = 0; r < WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = OCEANS; }
                if ( tileChar == 'o' ) { type = PLAINS; }
                if ( tileChar == 'M' ) { type = MOUNTAINS; }
                if ( tileChar == 'f' ) { type = FOREST; }
                if ( tileChar == 'h' ) { type = HILLS; }

                theWorld[r][c] = new TileImpl(type);
            }
        }
        return theWorld;
    }
}
