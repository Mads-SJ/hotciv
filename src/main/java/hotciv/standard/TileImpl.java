package hotciv.standard;

import hotciv.framework.Tile;

import static hotciv.framework.GameConstants.*;

public class TileImpl implements Tile {

    private final String typeString;

    public TileImpl(String typeString) {
        this.typeString = typeString;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    public String getResourceType() {
        return FOOD;
    }

    public int getResources() {
        switch (typeString){
            case PLAINS:
                return 3;
            case OCEANS:
                return 1;
        }
        return 0;
    }
}
