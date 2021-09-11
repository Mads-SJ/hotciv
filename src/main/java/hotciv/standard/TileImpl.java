package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {

    private final String typeString;

    public TileImpl(String typeString) {
        this.typeString = typeString;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }
}
