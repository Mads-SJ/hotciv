package hotciv.stub.broker;

import hotciv.framework.Tile;

import java.util.UUID;


public class StubBrokerTile implements Tile {
    final String id;

    public StubBrokerTile() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return "lava";
    }

    @Override
    public String getId() {
        return null;
    }
}
