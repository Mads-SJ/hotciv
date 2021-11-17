package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.Player;

import static hotciv.framework.OperationNames.GET_OWNER_OPERATION;
import static hotciv.framework.OperationNames.GET_PLAYER_IN_TURN_OPERATION;

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
        return 0;
    }

    @Override
    public int getTreasury() {
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
