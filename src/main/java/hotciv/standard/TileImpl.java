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
        switch (typeString){
            case PLAINS:
            case OCEANS:
                return FOOD;
            case MOUNTAINS:
            case HILLS:
            case FOREST:
                return PRODUCTION;
        }
        return "none"; // Burde måske smide exception?
    }

    public int getResources() {
        switch (typeString){
            case OCEANS:
            case MOUNTAINS:
                return 1;
            case HILLS:
                return 2;
            case PLAINS:
            case FOREST:
                return 3;
        }
        return 0;
    }
}
