package hotciv.standard;

import hotciv.common.strategy.TileStrategy;
import hotciv.framework.Tile;
import hotciv.variants.strategy.AlphaTileStrategy;

public class TileImpl implements Tile {

    private final String typeString;
    private TileStrategy tileStrategy;

    public TileImpl(String typeString, TileStrategy tileStrategy) {
        // init strategy der tjekker valid typestring
        // TODO: hvad gør vi ved ikke valid tiles? returnerer "tilfældig" valid tile? returnerer null?
        this.tileStrategy = tileStrategy;
        if (tileStrategy.isTileValid(typeString)) this.typeString = typeString;
        else this.typeString = "fake it";
    }

    @Override
    public String getTypeString() {
        return typeString;
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
