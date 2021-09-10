package hotciv.standard;

import hotciv.framework.Tile;

// TODO: Refaktorer MED GAME CONSTANTS!!!!!!!!!!!!!!!1 UBER ALLES
public class TileImpl implements Tile {

    private String typeString;

    public TileImpl(String typeString) {
        this.typeString = typeString;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }
}
