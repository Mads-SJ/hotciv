package hotciv.standard.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;

import javax.servlet.http.HttpServletResponse;

import static hotciv.framework.OperationNames.*;

public class UnitInvoker implements Invoker {
    private final Unit servant;
    private final Gson gson;

    public UnitInvoker(Unit servant) {
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

        if (requestObject.getOperationName().equals(UNIT_GET_TYPE_STRING_OPERATION)) {
            String typeString = servant.getTypeString();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(typeString));
        } else if (requestObject.getOperationName().equals(UNIT_GET_OWNER_OPERATION)) {
            Player owner = servant.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(owner));
        } else if (requestObject.getOperationName().equals(GET_MOVE_COUNT_OPERATION)) {
            int moveCount = servant.getMoveCount();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(moveCount));
        } else if (requestObject.getOperationName().equals(GET_DEFENSIVE_STRENGTH_OPERATION)) {
            int defensiveStrength = servant.getDefensiveStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(defensiveStrength));
        } else if (requestObject.getOperationName().equals(GET_ATTACKING_STRENGTH_OPERATION)) {
            int attackingStrength = servant.getAttackingStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(attackingStrength));
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
