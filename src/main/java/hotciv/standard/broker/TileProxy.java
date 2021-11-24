package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;

import static hotciv.framework.OperationNames.TILE_GET_TYPE_STRING_OPERATION;
import static hotciv.framework.OperationNames.UNIT_GET_TYPE_STRING_OPERATION;

public class TileProxy implements Tile, ClientProxy {
    private final static String TILE_OBJECT_ID = "singleton";
    private final Requestor requestor;

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }
    @Override
    public String getTypeString() {
        String typeString = requestor.sendRequestAndAwaitReply(TILE_OBJECT_ID, TILE_GET_TYPE_STRING_OPERATION, String.class);
        return typeString;
    }

    @Override
    public int getId() {
        return 0;
    }
}
