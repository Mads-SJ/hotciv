package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.OperationNames.*;

public class CityProxy implements City, ClientProxy {
    private static final String CITY_OBJECTID = "singleton";
    private final Requestor requestor;

    public CityProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(CITY_OBJECTID, GET_OWNER_OPERATION, Player.class);
        return owner;
    }

    @Override
    public int getSize() {
        int size = requestor.sendRequestAndAwaitReply(CITY_OBJECTID, GET_SIZE_OPERATION, int.class);
        return size;
    }

    @Override
    public int getTreasury() {
        int amount = requestor.sendRequestAndAwaitReply(CITY_OBJECTID, GET_TREASURY_OPERATION, int.class);
        return amount;
    }

    @Override
    public String getProduction() {
        String production = requestor.sendRequestAndAwaitReply(CITY_OBJECTID, GET_PRODUCTION_OPERATION, String.class);
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        String workforce = requestor.sendRequestAndAwaitReply(CITY_OBJECTID, GET_WORKFORCE_FOCUS_OPERATION, String.class);
        return workforce;
    }
}
