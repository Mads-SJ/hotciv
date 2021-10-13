package hotciv.variants.strategy;

import hotciv.common.strategy.TileStrategy;

import static hotciv.framework.GameConstants.*;

public class AlphaTileStrategy implements TileStrategy {
    @Override
    public String getResourceType(String typeString) {
        switch (typeString){
            case PLAINS:
            case OCEANS:
                return FOOD;
            case MOUNTAINS:
            case HILLS:
            case FOREST:
                return PRODUCTION;
        }
        return "none"; // Could throw exception instead?
    }

    @Override
    public int getResources(String typeString) {
        switch (typeString){
            case DESERT:
                return 0;
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
