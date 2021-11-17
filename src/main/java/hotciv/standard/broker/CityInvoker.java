package hotciv.standard.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import javax.servlet.http.HttpServletResponse;

import static hotciv.framework.OperationNames.*;

public class CityInvoker implements Invoker {
    private final City servant;
    private final Gson gson;

    public CityInvoker(City servant) {
        this.servant = servant;
        gson = new Gson();
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;

    /* As there is only one TeleMed instance (a singleton)
       the objectId is not used for anything in our case.
     */
        // Dispatching on all known operations
        // Each dispatch follows the same algorithm
        // a) retrieve parameters from json array (if any)
        // b) invoke servant method
        // c) populate a reply object with return values

        if (requestObject.getOperationName().equals(GET_OWNER_OPERATION)) {
            Player owner = servant.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(owner));
        } else if (requestObject.getOperationName().equals(GET_SIZE_OPERATION)) {
            int size = servant.getSize();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(size));
        } else if (requestObject.getOperationName().equals(GET_TREASURY_OPERATION)) {
            int amount = servant.getTreasury();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(amount));
        } else if (requestObject.getOperationName().equals(GET_PRODUCTION_OPERATION)) {
            String production = servant.getProduction();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(production));
        } else if (requestObject.getOperationName().equals(GET_WORKFORCE_FOCUS_OPERATION)) {
            String workforce = servant.getWorkforceFocus();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(workforce));
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
}
