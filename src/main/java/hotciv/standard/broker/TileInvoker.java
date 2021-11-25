package hotciv.standard.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;
import hotciv.stub.broker.StubBrokerTile;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.OperationNames.*;

public class TileInvoker implements Invoker {
    private final Gson gson;
    private NameService nameService;

    public TileInvoker(NameService nameService) {
        gson = new Gson();
        this.nameService = nameService;
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);

        String objectId = requestObject.getObjectId();

        ReplyObject reply;

        Tile tile = lookupTile(objectId);

        // Dispatching on all known operations
        // Each dispatch follows the same algorithm
        // a) retrieve parameters from json array (if any)
        // b) invoke servant method
        // c) populate a reply object with return values

        if (requestObject.getOperationName().equals(TILE_GET_TYPE_STRING_OPERATION)) {
            String typeString = tile.getTypeString();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(typeString));
        }
        else {
            // Unknown operation
            reply = new ReplyObject(HttpServletResponse.
                    SC_NOT_IMPLEMENTED,
                    "Server received unknown operation name: '"
                            + requestObject.getOperationName() + "'.");
        }

        // And marshall the reply
        return gson.toJson(reply);
    }

    private Tile lookupTile(String objectId) {
        return nameService.getTile(objectId);
    }
}
