package hotciv.stub.broker;

import hotciv.framework.Tile;

public class StubBrokerTile implements Tile {
    @Override
    public String getTypeString() {
        return "lava";
    }

    @Override
    public int getId() {
        return 0;
    }
}
