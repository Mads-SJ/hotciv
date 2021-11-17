package hotciv.standard.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import static hotciv.framework.OperationNames.*;

public class UnitProxy implements Unit, ClientProxy {
    private static final String UNIT_OBJECTID = "singleton";
    private final Requestor requestor;

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public String getTypeString() {
        String typeString = requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, UNIT_GET_TYPE_STRING_OPERATION, String.class);
        return typeString;
    }

    @Override
    public Player getOwner() {
        Player owner = requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, UNIT_GET_OWNER_OPERATION, Player.class);
        return owner;
    }

    @Override
    public int getMoveCount() {
        int moveCount = requestor.sendRequestAndAwaitReply(UNIT_OBJECTID, GET_MOVE_COUNT_OPERATION, int.class);
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
