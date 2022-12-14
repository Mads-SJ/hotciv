package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.OperationNames.*;

public class CityProxy implements City, ClientProxy {
    private final String CITY_OBJECT_ID;
    private final Requestor requestor;

    public CityProxy(Requestor requestor, String id) {
        this.requestor = requestor;
        CITY_OBJECT_ID = id;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(CITY_OBJECT_ID, CITY_GET_OWNER_OPERATION, Player.class);
        return owner;
    }

    @Override
    public int getSize() {
        int size = requestor.sendRequestAndAwaitReply(CITY_OBJECT_ID, CITY_GET_SIZE_OPERATION, int.class);
        return size;
    }

    @Override
    public int getTreasury() {
        int amount = requestor.sendRequestAndAwaitReply(CITY_OBJECT_ID, CITY_GET_TREASURY_OPERATION, int.class);
        return amount;
    }

    @Override
    public String getProduction() {
        String production = requestor.sendRequestAndAwaitReply(CITY_OBJECT_ID, CITY_GET_PRODUCTION_OPERATION, String.class);
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        String workforce = requestor.sendRequestAndAwaitReply(CITY_OBJECT_ID, CITY_GET_WORKFORCE_FOCUS_OPERATION, String.class);
        return workforce;
    }

    @Override
    public String getId() {
        return CITY_OBJECT_ID;
    }
}
