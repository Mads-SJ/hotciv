package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

import java.util.Map;

public interface WorldLayoutStrategy {
    String[] getWorldLayout();
    Map<Position, City> getCityMap();
}
