package hotciv.standard.broker;

import hotciv.framework.Tile;

public interface NameService {
    void putTile(String objectId, Tile tile);

    Tile getTile(String objectId);
}
