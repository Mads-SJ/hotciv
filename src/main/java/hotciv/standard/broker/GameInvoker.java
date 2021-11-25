package hotciv.standard.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletResponse;

import static hotciv.framework.OperationNames.*;

public class GameInvoker implements Invoker {
    private final Game servant;
    private final Gson gson;
    private final NameService nameService;

    public GameInvoker(Game servant, NameService nameService) {
        this.servant = servant;
        this.nameService = nameService;
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

        // Dispatching on all known operations
        // Each dispatch follows the same algorithm
        // a) retrieve parameters from json array (if any)
        // b) invoke servant method
        // c) populate a reply object with return values

        if (requestObject.getOperationName().equals(GAME_GET_WINNER_OPERATION)) {
            Player winner = servant.getWinner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(winner));
        } else if (requestObject.getOperationName().equals(GAME_GET_AGE_OPERATION)) {
            int age = servant.getAge();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(age));
        } else if (requestObject.getOperationName().equals(GAME_GET_PLAYER_IN_TURN_OPERATION)) {
            Player playerInTurn = servant.getPlayerInTurn();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(playerInTurn));
        } else if (requestObject.getOperationName().equals(GAME_MOVE_UNIT_OPERATION)) {
            Position from = gson.fromJson(array.get(0), Position.class);
            Position to = gson.fromJson(array.get(1), Position.class);
            boolean hasMoved = servant.moveUnit(from, to);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    gson.toJson(hasMoved));
        } else if (requestObject.getOperationName().equals(GAME_END_TURN_OPERATION)) {
            servant.endOfTurn();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    null);
        } else if (requestObject.getOperationName().equals(GAME_CHANGE_WORKFORCE_FOCUS_IN_CITY_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            String balance = gson.fromJson(array.get(1), String.class);
            servant.changeWorkForceFocusInCityAt(p, balance);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    null);
        } else if (requestObject.getOperationName().equals(GAME_CHANGE_PRODUCTION_IN_CITY_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            String unitType = gson.fromJson(array.get(1), String.class);
            servant.changeProductionInCityAt(p, unitType);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    null);
        } else if (requestObject.getOperationName().equals(GAME_PERFORM_UNIT_ACTION_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            servant.performUnitActionAt(p);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    null);
        }
        else if (requestObject.getOperationName().equals(GAME_GET_TILE_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            Tile tile = servant.getTileAt(p);
            String id = tile.getId();
            nameService.putTile(id, tile);

            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    id);
        } else if (requestObject.getOperationName().equals(GAME_GET_UNIT_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            Unit unit = servant.getUnitAt(p);
            String id = unit.getId();
            nameService.putUnit(id, unit);

            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    id);
        } else if (requestObject.getOperationName().equals(GAME_GET_CITY_AT_OPERATION)) {
            Position p = gson.fromJson(array.get(0), Position.class);
            City city = servant.getCityAt(p);
            String id = city.getId();
            nameService.putCity(id, city);

            reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                    id);
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
