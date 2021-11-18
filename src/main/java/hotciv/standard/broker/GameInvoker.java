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

public class GameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;

    public GameInvoker(Game servant) {
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
        } else if (requestObject.getOperationName().equals(GET_AGE_OPERATION)) {
            int age = servant.getAge();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(age));
        } else if (requestObject.getOperationName().equals(GET_PLAYER_IN_TURN_OPERATION)) {
            Player playerInTurn = servant.getPlayerInTurn();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(playerInTurn));
        } /* else if (requestObject.getOperationName().equals(GET_CITY_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            City city = servant.getCityAt(p);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(city));
        } */
        else if (requestObject.getOperationName().equals(MOVE_UNIT_OPERATION)) {
            Position from = gson.fromJson(array.get(0), Position.class);
            Position to = gson.fromJson(array.get(1), Position.class);
            boolean hasMoved = servant.moveUnit(from, to);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(hasMoved));
        } else if (requestObject.getOperationName().equals(END_TURN_OPERATION)) {
            servant.endOfTurn();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(requestObject.getOperationName())); // TODO er dette ok ved void
        } else if (requestObject.getOperationName().equals(CHANGE_WORKFORCE_FOCUS_IN_CITY_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            String balance = gson.fromJson(array.get(1), String.class);
            servant.changeWorkForceFocusInCityAt(p, balance);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(requestObject.getOperationName()));
        } else if (requestObject.getOperationName().equals(CHANGE_PRODUCTION_IN_CITY_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            String unitType = gson.fromJson(array.get(1), String.class);
            servant.changeProductionInCityAt(p, unitType);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(requestObject.getOperationName()));
        } else if (requestObject.getOperationName().equals(PERFORM_UNIT_ACTION_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            servant.performUnitActionAt(p);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(requestObject.getOperationName()));
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
