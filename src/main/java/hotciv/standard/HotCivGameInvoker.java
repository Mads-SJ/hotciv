package hotciv.standard;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.framework.Player;

import javax.servlet.http.HttpServletResponse;

import static hotciv.framework.OperationNames.*;

public class HotCivGameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;

    public HotCivGameInvoker(Game servant) {
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

        if (requestObject.getOperationName().equals(GET_WINNER_OPERATION)) {
            Player winner = servant.getWinner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(winner));

        }  else {
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
