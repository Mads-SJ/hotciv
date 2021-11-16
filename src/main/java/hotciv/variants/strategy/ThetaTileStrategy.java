package hotciv.variants.strategy;

import hotciv.common.strategy.TileStrategy;

import static hotciv.framework.GameConstants.DESERT;
import static hotciv.framework.GameConstants.FOOD;

public class ThetaTileStrategy implements TileStrategy {
    AlphaTileStrategy alphaTileStrategy;

    public ThetaTileStrategy() {
        alphaTileStrategy = new AlphaTileStrategy();
    }

    @Override
    public String getResourceType(String typeString) {
        if (typeString.equals(DESERT)) return FOOD; // DESERT has no resource type and is therefore set to FOOD
        return alphaTileStrategy.getResourceType(typeString);
    }

    @Override
    public int getResources(String typeString) {
        if (typeString.equals(DESERT)) return 0;
        return alphaTileStrategy.getResources(typeString);
    }

    @Override
    public boolean isTileValid(String typeString) {
        if (typeString.equals(DESERT)) return true;
        return alphaTileStrategy.isTileValid(typeString);
    }
}
