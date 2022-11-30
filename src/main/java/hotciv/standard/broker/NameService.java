package hotciv.standard.broker;

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public interface NameService {
    void putTile(String objectId, Tile tile);
    Tile getTile(String objectId);
    void putUnit(String objectId, Unit unit);
    Unit getUnit(String objectId);
    void putCity(String objectId, City city);
    City getCity(String objectId);
}
