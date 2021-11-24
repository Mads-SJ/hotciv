package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;

import static hotciv.framework.OperationNames.TILE_GET_TYPE_STRING_OPERATION;

public class TileProxy implements Tile, ClientProxy {
    private final String TILE_OBJECT_ID;
    private final Requestor requestor;

    public TileProxy(Requestor requestor, String id) {
        this.requestor = requestor;
        TILE_OBJECT_ID = id;
    }
    @Override
    public String getTypeString() {
        String typeString = requestor.sendRequestAndAwaitReply(TILE_OBJECT_ID, TILE_GET_TYPE_STRING_OPERATION, String.class);
        return typeString;
    }

    @Override
    public String getId() {
        return null;
    }
}
