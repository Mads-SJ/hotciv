package hotciv.standard.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static hotciv.framework.OperationNames.*;

public class RootInvoker implements Invoker {
    private final Gson gson;
    private final Map<String, Invoker> invokerMap;
    private final NameService nameService;

    public RootInvoker(Game game) {
        gson = new Gson();

        nameService = new InMemoryNameService();

        invokerMap = new HashMap<>();

        Invoker gameInvoker = new GameInvoker(game, nameService);
        Invoker cityInvoker = new CityInvoker(nameService);
        Invoker unitInvoker = new UnitInvoker(nameService);
        Invoker tileInvoker = new TileInvoker(nameService);

        invokerMap.put(GAME_PREFIX, gameInvoker);
        invokerMap.put(CITY_PREFIX, cityInvoker);
        invokerMap.put(UNIT_PREFIX, unitInvoker);
        invokerMap.put(TILE_PREFIX, tileInvoker);
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        String operationName = requestObject.getOperationName();

        // Identify the invoker to use
        String type = operationName.substring(0, operationName.indexOf(SEPERATOR));
        Invoker subInvoker = invokerMap.get(type);

        String reply;

        try {
            reply = subInvoker.handleRequest(request);
        }
        catch (Exception e){
            reply = gson.toJson(
                    new ReplyObject(
                            HttpServletResponse.SC_NOT_FOUND,
                            e.getMessage()));
        }

        return reply;
    }
}
