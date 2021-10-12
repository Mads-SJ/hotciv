package hotciv.common.strategy;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.Map;

public interface WorldLayoutStrategy {
    String[] getWorldLayout();
    Map<Position, City> getCityMap();
    UnitImpl[][] getUnitPositions();
}
