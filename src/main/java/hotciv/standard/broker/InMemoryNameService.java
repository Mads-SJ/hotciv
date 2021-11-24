package hotciv.standard.broker;

import hotciv.framework.Tile;

import java.util.HashMap;
import java.util.Map;

public class InMemoryNameService implements NameService{
    private Map<String, Tile> tileMap;

    public InMemoryNameService() {
        this.tileMap = new HashMap<>();
    }

    @Override
    public void putTile(String objectId, Tile tile) {
        tileMap.put(objectId, tile);
    }

    @Override
    public Tile getTile(String objectId) {
        return tileMap.get(objectId);
    }
}
