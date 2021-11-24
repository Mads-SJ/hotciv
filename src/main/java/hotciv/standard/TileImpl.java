package hotciv.standard;

import hotciv.common.strategy.TileStrategy;
import hotciv.framework.Tile;

import static hotciv.framework.GameConstants.PLAINS;

public class TileImpl implements Tile {

    private final String typeString;
    private TileStrategy tileStrategy;

    public TileImpl(String typeString, TileStrategy tileStrategy) {
        this.tileStrategy = tileStrategy;
        if (tileStrategy.isTileValid(typeString)) this.typeString = typeString;
        else this.typeString = PLAINS; // if tile is not valid the tile type is set to PLAINS
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public String getId() {
        return null;
    }

    public String getResourceType() {
        // add to strategy with below
        // resource type according to table 36.1
        return tileStrategy.getResourceType(typeString);
    }

    public int getResources() {
        // add to strategy with above
        // resource amounts according to table 36.1
        return tileStrategy.getResources(typeString);
    }
}
